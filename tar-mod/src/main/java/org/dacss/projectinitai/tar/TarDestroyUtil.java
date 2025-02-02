package org.dacss.projectinitai.tar;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class TarDestroyUtil {

    static String destroyTarFile() throws IOException {
        File tarFile = new File("/path/to/tarfile.tar");
        File destDir = new File("/path/to/destination");
        if (verifyExtraction(destDir)) {
            if (Files.deleteIfExists(tarFile.toPath())) {
                return "Tar file deleted: " + tarFile.getAbsolutePath();
            } else {
                return "Failed to delete tar file: " + tarFile.getAbsolutePath();
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
