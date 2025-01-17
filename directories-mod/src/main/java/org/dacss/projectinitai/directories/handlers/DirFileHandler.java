package org.dacss.projectinitai.directories.handlers;

import org.dacss.projectinitai.directories.utilities.CreateDirFileUtil;
import org.dacss.projectinitai.directories.utilities.DestroyDirFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link DirFileHandler}</h1>
 * <p>
 * Backend handler for directories and files to be called from the service.
 * </p>
 */
public class DirFileHandler {

    private static final Logger log = LoggerFactory.getLogger(DirFileHandler.class);

    /**
     * Creates a directory.
     *
     * @param path the directory path
     */
    public void createDirectory(String path) {
        try {
            CreateDirFileUtil.createDirectory(path);
        } catch (Exception e) {
            log.error("Error creating directory: {}", path, e);
        }
    }

    /**
     * Creates a file.
     *
     * @param dirPath  the directory path
     * @param fileName the file name
     */
    public void createFile(String dirPath, String fileName) {
        try {
            CreateDirFileUtil.createFile(dirPath, fileName);
        } catch (Exception e) {
            log.error("Error creating file: {} in directory: {}", fileName, dirPath, e);
        }
    }

    /**
     * Deletes a directory and its contents.
     *
     * @param path the directory path
     */
    public void deleteDirectory(String path) {
        try {
            DestroyDirFileUtil.deleteDirectory(path);
        } catch (Exception e) {
            log.error("Error deleting directory: {}", path, e);
        }
    }

    /**
     * Deletes a file.
     *
     * @param dirPath  the directory path
     * @param fileName the file name
     */
    public void deleteFile(String dirPath, String fileName) {
        try {
            DestroyDirFileUtil.deleteFile(dirPath, fileName);
        } catch (Exception e) {
            log.error("Error deleting file: {} in directory: {}", fileName, dirPath, e);
        }
    }
}
