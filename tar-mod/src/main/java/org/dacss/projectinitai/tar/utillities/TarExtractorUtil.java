package org.dacss.projectinitai.tar.utillities;
/**/

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <h1>{@link TarExtractorUtil}</h1>
 * Utility class to extract a tar file.
 */
public class TarExtractorUtil {

    /**
     * {@link #extractTarFile(File, File)}
     * Method to extract a tar file to a destination directory.
     *
     * @param tarFile - the tar file to extract
     * @param destDir - the destination directory
     * @throws IOException - if an I/O error occurs
     */
    public static void extractTarFile(File tarFile, File destDir) throws IOException {
        try (FileInputStream FIS = new FileInputStream(tarFile);
             TarArchiveInputStream TAIS = new TarArchiveInputStream(FIS)) {
            TarArchiveEntry entry;
            while ((entry = TAIS.getNextEntry()) != null) {
                File outputFile = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    if (!outputFile.exists() && !outputFile.mkdirs()) {
                        throw new IOException(STR."Failed to create directory \{outputFile.getAbsolutePath()}");
                    }
                } else {
                    File parent = outputFile.getParentFile();
                    if (!parent.exists() && !parent.mkdirs()) {
                        throw new IOException(STR."Failed to create directory \{parent.getAbsolutePath()}");
                    }
                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = TAIS.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
}
