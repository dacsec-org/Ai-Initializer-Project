package org.dacss.projectinitai.zip;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>{@link ZipDestroyUtil}</h1>
 * <p>
 *     Utility class for destroying a zip file.
 * </p>
 */
@Component
public class ZipDestroyUtil {

    static String destroyZipFile() throws IOException {
        File zipFile = new File("/path/to/zipfile.zip");
        File destDir = new File("/path/to/destination");
        if (verifyExtraction(destDir)) {
            if (Files.deleteIfExists(zipFile.toPath())) {
                return "Zip file deleted: " + zipFile.getAbsolutePath();
            } else {
                return "Failed to delete zip file: " + zipFile.getAbsolutePath();
            }
        } else {
            return "Extraction verification failed for directory: " + destDir.getAbsolutePath();
        }
    }

    static boolean verifyExtraction(File destDir) {
        if (!destDir.exists() || !destDir.isDirectory()) {
            return false;
        }
        String[] files = destDir.list();
        return files != null && files.length > 0;
    }
}
