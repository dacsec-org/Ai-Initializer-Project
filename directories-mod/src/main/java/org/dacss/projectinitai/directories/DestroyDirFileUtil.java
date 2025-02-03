package org.dacss.projectinitai.directories;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DestroyDirFileUtil}</h1>
 * Utility class for destroying directories and files.
 * This class provides methods to delete directories and files in a non-blocking manner using Reactor.
 */
@Component
public class DestroyDirFileUtil {

    private static final Logger log = LoggerFactory.getLogger(DestroyDirFileUtil.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    /**
     * <h3>{@link #deleteDirectory(String)}</h3>
     * Deletes a directory at the specified path.
     * If the directory does not exist, it logs a message indicating so.
     * This method runs in a non-blocking manner.
     *
     * @param path the directory path
     * @return a {@link Flux} that completes when the directory is deleted
     */
    public static Flux<Object> deleteDirectory(String path) {
        return Flux.create(sink -> {
            File dir = new File(path);
            if (dir.exists()) {
                deleteRecursively(dir);
            } else {
                log.info("{}Directory does not exist: {}{}", RED, path, RESET);
            }
            sink.next(new Object());
            sink.complete();
        });
    }

    /**
     * <h3>{@link #deleteFile(String, String)}</h3>
     * Deletes a file with the specified name in the specified directory.
     * If the file does not exist, it logs a message indicating so.
     * This method runs in a non-blocking manner.
     *
     * @param dirPath  the directory path
     * @param fileName the file name
     * @return a {@link Flux} that completes when the file is deleted
     */
    public static Flux<Object> deleteFile(String dirPath, String fileName) {
        return Flux.create(sink -> {
            File file = new File(dirPath, fileName);
            if (file.exists()) {
                if (!file.delete()) {
                    log.error("{}Failed to delete file: {}{}", RED, fileName, RESET);
                } else {
                    log.info("{}File deleted successfully: {}{}", GREEN, fileName, RESET);
                }
            } else {
                log.info("{}File does not exist: {}{}", RED, fileName, RESET);
            }
            sink.next(new Object());
            sink.complete();
        });
    }

    /**
     * <h3>{@link #deleteRecursively(File)}</h3>
     * Recursively deletes the directory or file.
     * If the file is a directory, it deletes all its contents first.
     * This method is used internally by {@link #deleteDirectory(String)}.
     *
     * @param file the directory or file to delete
     */
    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteRecursively(f);
                }
            }
        }
        if (!file.delete()) {
            log.error("{}Failed to delete: {}{}", RED, file.getPath(), RESET);
        } else {
            log.info("{}Deleted successfully: {}{}", GREEN, file.getPath(), RESET);
        }
    }
}
