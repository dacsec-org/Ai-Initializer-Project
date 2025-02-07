//package org.dacss.projectinitai.tar;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//import reactor.core.scheduler.Schedulers;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.Objects;
//
///**
// * <h1>{@link TarDestroy}</h1>
// * This class provides methods to delete tar files and verify their extraction.
// */
//public class TarDestroy {
//
//    private static final Logger log = LoggerFactory.getLogger(TarDestroy.class);
//    private static final String RED = "\u001B[31m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String RESET = "\u001B[0m";
//
//    private static File tarFile;
//    private static File destDir;
//
//    /**
//     * <h3>{@link #TarDestroy(String)}</h3>
//     * Constructor that sets the tar file and destination directory.
//     *
//     * @param tarFilePath the path to the tar file
//     */
//    public TarDestroy(String tarFilePath) {
//        tarFile = new File(tarFilePath);
//        destDir = tarFile.getParentFile();
//    }
//
//    /**
//     * <h3>{@link #destroyTarFile()}</h3>
//     * Deletes the specified tar file and matching files if the extraction verification is successful.
//     *
//     * @return a {@link Flux} that emits a message indicating the success or failure of the tar file deletion
//     */
//    static Flux<Object> destroyTarFile() {
//        return Flux.create(sink -> {
//            try {
//                if (verifyExtraction(destDir)) {
//                    String tarFileNamePrefix = tarFile.getName().split("\\.tar")[0];
//                    File[] matchingFiles = Objects.requireNonNull(destDir.listFiles((dir, name) -> name.startsWith(tarFileNamePrefix) && !name.equals(tarFile.getName())));
//
//                    boolean allDeleted = true;
//                    for (File file : matchingFiles) {
//                        if (!Files.deleteIfExists(file.toPath())) {
//                            allDeleted = false;
//                            String failureMessage = "Failed to delete file: " + file.getAbsolutePath();
//                            log.error("{}{}{}", RED, failureMessage, RESET);
//                            sink.next(failureMessage);
//                        }
//                    }
//
//                    if (allDeleted && Files.deleteIfExists(tarFile.toPath())) {
//                        String successMessage = "All matching files deleted successfully";
//                        log.info("{}{}{}", GREEN, successMessage, RESET);
//                        sink.next(successMessage);
//                    } else {
//                        String failureMessage = "Failed to delete tar file: " + tarFile.getAbsolutePath();
//                        log.error("{}{}{}", RED, failureMessage, RESET);
//                        sink.next(failureMessage);
//                    }
//                } else {
//                    String verificationFailureMessage = "Extraction verification failed for directory: " + destDir.getAbsolutePath();
//                    log.error("{}{}{}", RED, verificationFailureMessage, RESET);
//                    sink.next(verificationFailureMessage);
//                }
//                sink.complete();
//            } catch (IOException tarDestroyExc) {
//                String errorMessage = "Error deleting files: " + tarDestroyExc.getMessage();
//                log.error("{}{}{}", RED, errorMessage, RESET, tarDestroyExc);
//                sink.error(tarDestroyExc);
//            }
//        }).subscribeOn(Schedulers.boundedElastic());
//    }
//
//    /**
//     * <h3>{@link #verifyExtraction(File)}</h3>
//     * Verifies the extraction of the tar file.
//     * This method checks if the destination directory exists and contains files.
//     *
//     * @param destDir the destination directory
//     * @return true if the extraction is verified, false otherwise
//     */
//    static boolean verifyExtraction(File destDir) {
//        if (!destDir.exists() || !destDir.isDirectory()) {
//            return false;
//        }
//        String[] files = destDir.list();
//        return files != null && files.length > 0;
//    }
//}
