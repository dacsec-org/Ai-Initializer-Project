package org.dacss.projectinitai.utillities;
/**/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>{@link TarDestroyUtil}</h1>
 * Utility class to destroy a tar file.
 */
public class TarDestroyUtil {

    /**
     * Verifies the extraction of a tar file.
     *
     * @param destDir - the directory to check for extraction
     * @return - true if the extraction is verified, false otherwise
     */
    public static boolean verifyExtraction(File destDir) {
        if (!destDir.exists() || !destDir.isDirectory()) {
            return false;
        }
        String[] files = destDir.list();
        return files != null && files.length > 0;
    }

    /**
     * Destroys a tar file by verifying the extraction, then deleting the file.
     *
     * @param tarFile - the tar file to destroy
     * @param destDir - the directory to check for extraction
     * @return - a message indicating the result of the operation
     */
    public static String destroyTarFile(File tarFile, File destDir) {
        if (verifyExtraction(destDir)) {
            try {
                if (Files.deleteIfExists(tarFile.toPath())) {
                    return STR."Tar file deleted: \{tarFile.getAbsolutePath()}";
                } else {
                    return STR."Failed to delete tar file: \{tarFile.getAbsolutePath()}";
                }
            } catch (IOException e) {
                return STR."Error deleting tar file: \{e.getMessage()}";
            }
        } else {
            return STR."Extraction verification failed for directory: \{destDir.getAbsolutePath()}";
        }
    }
}
