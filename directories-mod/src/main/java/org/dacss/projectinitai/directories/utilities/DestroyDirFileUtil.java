package org.dacss.projectinitai.directories.utilities;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link DestroyDirFileUtil}</h1>
 * <p>
 * Utility class for destroying directories and files.
 * </p>
 */
public class DestroyDirFileUtil {

    private static final Logger log = LoggerFactory.getLogger(DestroyDirFileUtil.class);

    /**
     * {@link #deleteDirectory(String)}
     *
     * @param path the directory path
     */
    public static void deleteDirectory(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            deleteRecursively(dir);
        } else {
            log.info("Directory does not exist: {}", path);
        }
    }

    /**
     * {@link #deleteFile(String, String)}
     *
     * @param dirPath  the directory path
     * @param fileName the file name
     */
    public static void deleteFile(String dirPath, String fileName) {
        File file = new File(dirPath, fileName);
        if (file.exists()) {
            if (!file.delete()) {
                log.error("Failed to delete file: {}", fileName);
            } else {
                log.info("File deleted successfully: {}", fileName);
            }
        } else {
            log.info("File does not exist: {}", fileName);
        }
    }

    /**
     * {@link #deleteRecursively(File)}
     *
     * Recursively deletes the directory or file.
     * @param file the directory or file
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
            log.error("Failed to delete: {}", file.getPath());
        } else {
            log.info("Deleted: {}", file.getPath());
        }
    }
}
