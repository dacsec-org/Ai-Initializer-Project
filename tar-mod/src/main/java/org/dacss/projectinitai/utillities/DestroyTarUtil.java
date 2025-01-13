package org.dacss.projectinitai.utillities;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>{@link DestroyTarUtil}</h1>
 * Utility class to destroy a tar file.
 */
@UtilityClass
public class DestroyTarUtil {

    /**
     * Verifies the extraction of a tar file.
     * @param destDir - the directory to check for extraction
     * @return - true if the extraction is verified, false otherwise
     */
    public boolean verifyExtraction(File destDir) {
        if (!destDir.exists() || !destDir.isDirectory()) {
            return false;
        }
        String[] files = destDir.list();
        return files != null && files.length > 0;
    }

    /**
     * Destroys a tar file by verifying the extraction, then deleting the file.
     * @param tarFile - the tar file to destroy
     * @param destDir - the directory to check for extraction
     * @return - a message indicating the result of the operation
     */
    public String destroyTarFile(File tarFile, File destDir) {
        if (verifyExtraction(destDir)) {
            try {
                if (Files.deleteIfExists(tarFile.toPath())) {
                    return "Tar file deleted: " + tarFile.getAbsolutePath();
                } else {
                    return "Failed to delete tar file: " + tarFile.getAbsolutePath();
                }
            } catch (IOException e) {
                return "Error deleting tar file: " + e.getMessage();
            }
        } else {
            return "Extraction verification failed for directory: " + destDir.getAbsolutePath();
        }
    }
}
