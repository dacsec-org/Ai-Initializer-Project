//package org.dacss.projectinitai.tar;
//
//import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
//import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//import reactor.core.scheduler.Schedulers;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * <h1>{@link TarExtractor}</h1>
// * This class provides methods to extract the contents of tar archives.
// */
//public class TarExtractor {
//
//    private static final Logger log = LoggerFactory.getLogger(TarExtractor.class);
//    private static final String RED = "\u001B[31m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String RESET = "\u001B[0m";
//
//    private static File tarFile;
//    private static File destDir;
//
//    /**
//     * <h3>{@link #TarExtractor(String)}</h3>
//     * Constructor that sets the tar file and destination directory.
//     *
//     * @param tarFilePath the path to the tar file
//     */
//    public TarExtractor(String tarFilePath) {
//        tarFile = new File(tarFilePath);
//        destDir = tarFile.getParentFile();
//    }
//
//    /**
//     * <h3>{@link #extractTarFile()}</h3>
//     * Extracts the contents of the specified tar file to the destination directory.
//     *
//     * @return a {@link Flux} that emits a message indicating the success or failure of the tar file extraction
//     */
//    static Flux<Object> extractTarFile() {
//        return Flux.create(sink -> {
//            try (FileInputStream fis = new FileInputStream(tarFile);
//                 TarArchiveInputStream tais = new TarArchiveInputStream(fis)) {
//                TarArchiveEntry entry;
//                while ((entry = tais.getNextEntry()) != null) {
//                    File outputFile = new File(destDir, entry.getName());
//                    if (entry.isDirectory()) {
//                        if (!outputFile.exists() && !outputFile.mkdirs()) {
//                            String errorMessage = "Failed to create directory " + outputFile.getAbsolutePath();
//                            log.error("{}{}{}", RED, errorMessage, RESET);
//                            sink.error(new IOException(errorMessage));
//                            return;
//                        }
//                    } else {
//                        File parent = outputFile.getParentFile();
//                        if (!parent.exists() && !parent.mkdirs()) {
//                            String errorMessage = "Failed to create directory " + parent.getAbsolutePath();
//                            log.error("{}{}{}", RED, errorMessage, RESET);
//                            sink.error(new IOException(errorMessage));
//                            return;
//                        }
//                        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
//                            byte[] buffer = new byte[1024];
//                            int len;
//                            while ((len = tais.read(buffer)) != -1) {
//                                fos.write(buffer, 0, len);
//                            }
//                        }
//                    }
//                }
//                String successMessage = "Tar file extracted successfully";
//                log.info("{}{}{}", GREEN, successMessage, RESET);
//                sink.next(successMessage);
//                sink.complete();
//            } catch (IOException tarExtractorUtilExc) {
//                String errorMessage = "Error extracting tar file: " + tarExtractorUtilExc.getMessage();
//                log.error("{}{}{}", RED, errorMessage, RESET);
//                sink.error(tarExtractorUtilExc);
//            }
//        }).subscribeOn(Schedulers.boundedElastic());
//    }
//}
