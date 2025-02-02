package org.dacss.projectinitai.zip;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * <h1>{@link ZipExtractorUtil}</h1>
 * <p>
 *     Utility class for extracting files from a zip file.
 * </p>
 */
@Component
public class ZipExtractorUtil {

    static String extractZipFile() throws IOException {
        File zipFile = new File("/path/to/zipfile.zip");
        File destDir = new File("/path/to/destination");
        try (FileInputStream fis = new FileInputStream(zipFile);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
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
                        while ((len = zis.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
            return "Zip file extracted successfully";
        }
    }
}
