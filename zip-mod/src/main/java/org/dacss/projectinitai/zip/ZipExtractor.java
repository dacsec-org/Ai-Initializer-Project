package org.dacss.projectinitai.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * <h1>{@link ZipExtractor}</h1>
 * This class is responsible for extracting zip files and optionally deleting the original zip file.
 */
@Component
public class ZipExtractor {

    private static final Logger log = LoggerFactory.getLogger(ZipExtractor.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    private static File zipFile;
    private static File destDir;

    /**
     * <h3>{@link ZipExtractor}</h3>
     *
     * @param zipFilePath The path to the zip file to be extracted.
     */
    public ZipExtractor(String zipFilePath) {
        zipFile = new File(zipFilePath);
        destDir = zipFile.getParentFile();
    }

    /**
     * <h3>{@link #extractZipFile()}</h3>
     * Extracts the zip file to the destination directory.
     *
     * @return A {@link Flux} that emits a success message if the zip file is extracted successfully.
     */
    public static Flux<Object> extractZipFile() {
        return Flux.create(sink -> {
            try (FileInputStream fis = new FileInputStream(zipFile);
                 ZipInputStream zis = new ZipInputStream(fis)) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    File outputFile = new File(destDir, entry.getName());
                    if (entry.isDirectory()) {
                        if (!outputFile.exists() && !outputFile.mkdirs()) {
                            String errorMessage = "Failed to create directory " + outputFile.getAbsolutePath();
                            log.error("{}{}{}", RED, errorMessage, RESET);
                            sink.error(new IOException(errorMessage));
                            return;
                        }
                    } else {
                        File parent = outputFile.getParentFile();
                        if (!parent.exists() && !parent.mkdirs()) {
                            String errorMessage = "Failed to create directory " + parent.getAbsolutePath();
                            log.error("{}{}{}", RED, errorMessage, RESET);
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
                log.info("{}{}{}", GREEN, successMessage, RESET);
                sink.next(successMessage);
                sink.complete();
            } catch (IOException zipExtractExc) {
                String errorMessage = "Error extracting zip file: " + zipExtractExc.getMessage();
                log.error("{}{}{}", RED, errorMessage, RESET, zipExtractExc);
                sink.error(zipExtractExc);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #destroyZipFile()}</h3>
     * Deletes the zip file if the extracted version exists.
     *
     * @return A {@link Flux} that emits a success message if the zip file is deleted successfully.
     */
    public static Flux<Object> destroyZipFile() {
        return Flux.create(sink -> {
            try {
                if (verifyExtraction(destDir)) {
                    if (Files.deleteIfExists(zipFile.toPath())) {
                        String successMessage = "Zip file deleted successfully";
                        log.info("{}{}{}", GREEN, successMessage, RESET);
                        sink.next(successMessage);
                    } else {
                        String failureMessage = "Failed to delete zip file: " + zipFile.getAbsolutePath();
                        log.error("{}{}{}", RED, failureMessage, RESET);
                        sink.next(failureMessage);
                    }
                } else {
                    String verificationFailureMessage = "Extraction verification failed for directory: " + destDir.getAbsolutePath();
                    log.error("{}{}{}", RED, verificationFailureMessage, RESET);
                    sink.next(verificationFailureMessage);
                }
                sink.complete();
            } catch (IOException zipDestroyExc) {
                String errorMessage = "Error deleting zip file: " + zipDestroyExc.getMessage();
                log.error("{}{}{}", RED, errorMessage, RESET, zipDestroyExc);
                sink.error(zipDestroyExc);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #verifyExtraction(File)}</h3>
     * Verifies that the extraction process was successful.
     *
     * @param destDir The directory where the files were extracted.
     * @return {@code true} if the extraction was successful, {@code false} otherwise.
     */
    static boolean verifyExtraction(File destDir) {
        if (!destDir.exists() || !destDir.isDirectory()) {
            return false;
        }
        String[] files = destDir.list();
        return files != null && files.length > 0;
    }
}
