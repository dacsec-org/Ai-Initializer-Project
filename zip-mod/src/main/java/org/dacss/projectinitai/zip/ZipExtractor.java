package org.dacss.projectinitai.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor implements ZipIface {

    private static final Logger log = LoggerFactory.getLogger(ZipExtractor.class);
    private static final Pattern ZIP_PATTERN = Pattern.compile(".*\\.zip$");
    private final File zipFile;

    public ZipExtractor(String zipFilePath) {
        this.zipFile = new File(zipFilePath);
    }

    @Override
    public Flux<Object> processZip(String compressedLLM) {
        return Flux.create(sink -> {
            File zipFile = new File(compressedLLM);
            File destDir = zipFile.getParentFile();

            if (!ZIP_PATTERN.matcher(zipFile.getName()).matches()) {
                String errorMessage = "File is not a zip file: " + zipFile.getAbsolutePath();
                log.error("{}", errorMessage);
                sink.error(new IOException(errorMessage));
                return;
            }

            try (FileInputStream fis = new FileInputStream(zipFile);
                 ZipInputStream zis = new ZipInputStream(fis)) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    File outputFile = new File(destDir, entry.getName());
                    if (entry.isDirectory()) {
                        if (!outputFile.exists() && !outputFile.mkdirs()) {
                            String errorMessage = "Failed to create directory " + outputFile.getAbsolutePath();
                            log.error("{}", errorMessage);
                            sink.error(new IOException(errorMessage));
                            return;
                        }
                    } else {
                        File parent = outputFile.getParentFile();
                        if (!parent.exists() && !parent.mkdirs()) {
                            String errorMessage = "Failed to create directory " + parent.getAbsolutePath();
                            log.error("{}", errorMessage);
                            sink.error(new IOException(errorMessage));
                            return;
                        }
                        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = zis.read(buffer)) != -1) {
                                fos.write(buffer, 0, len);
                            }
                        }
                    }
                }
                String successMessage = "Zip file extracted successfully";
                log.info("{}", successMessage);
                sink.next(successMessage);
                sink.complete();
            } catch (IOException zipExtractExc) {
                String errorMessage = "Error extracting zip file: " + zipExtractExc.getMessage();
                log.error("{}", errorMessage, zipExtractExc);
                sink.error(zipExtractExc);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
