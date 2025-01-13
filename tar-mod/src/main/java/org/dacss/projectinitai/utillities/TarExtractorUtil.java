package org.dacss.projectinitai.utillities;

import lombok.experimental.UtilityClass;
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
@UtilityClass
public class TarExtractorUtil {

    public void extractTarFile(File tarFile, File destDir) throws IOException {
        try (FileInputStream fis = new FileInputStream(tarFile);
             TarArchiveInputStream tais = new TarArchiveInputStream(fis)) {
            TarArchiveEntry entry;
            while ((entry = tais.getNextEntry()) != null) {
                File outputFile = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    if (!outputFile.exists() && !outputFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + outputFile.getAbsolutePath());
                    }
                } else {
                    File parent = outputFile.getParentFile();
                    if (!parent.exists() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent.getAbsolutePath());
                    }
                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = tais.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
}
