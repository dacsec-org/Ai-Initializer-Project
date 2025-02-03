package org.dacss.projectinitai.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <h1>{@link ZipCompressor}</h1>
 * This class is responsible for compressing directories into zip files.
 */
@Component
public class ZipCompressor {

    private static final Logger log = LoggerFactory.getLogger(ZipCompressor.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    private static File sourceDir;
    private static File zipFile;

    /**
     * <h3>{@link ZipCompressor}</h3>
     *
     * @param sourceDirPath The path of the directory to be compressed.
     * @param zipFilePath   The path of the zip file to be created.
     */
    public ZipCompressor(String sourceDirPath, String zipFilePath) {
        sourceDir = new File(sourceDirPath);
        zipFile = new File(zipFilePath);
    }

    /**
     * <h3>{@link #createZipFile()}</h3>
     * Creates a zip file from the source directory.
     *
     * @return A {@link Flux} that emits a success message if the zip file is created successfully.
     */
    public static Flux<Object> createZipFile() {
        return Flux.create(sink -> {
            try (FileOutputStream fos = new FileOutputStream(zipFile);
                 ZipOutputStream zos = new ZipOutputStream(fos)) {
                addFilesToZip(zos, sourceDir, "");
                String successMessage = "Zip file created successfully";
                log.info("{}{}{}", GREEN, successMessage, RESET);
                sink.next(successMessage);
                sink.complete();
            } catch (IOException e) {
                String errorMessage = "Error creating zip file: " + e.getMessage();
                log.error("{}{}{}", RED, errorMessage, RESET, e);
                sink.error(e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #addFilesToZip(ZipOutputStream, File, String)}</h3>
     * Adds files to the zip output stream.
     *
     * @param zos    The {@link ZipOutputStream} to write to.
     * @param file   The {@link File} to be added to the zip file.
     * @param parent The parent directory of the file.
     * @throws IOException If an I/O error occurs.
     */
    private static void addFilesToZip(ZipOutputStream zos, File file, String parent) throws IOException {
        String entryName = parent + file.getName();
        ZipEntry zipEntry = new ZipEntry(entryName);
        zos.putNextEntry(zipEntry);

        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, len);
                }
            }
            zos.closeEntry();
        } else if (file.isDirectory()) {
            zos.closeEntry();
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                addFilesToZip(zos, childFile, entryName + "/");
            }
        }
    }
}
