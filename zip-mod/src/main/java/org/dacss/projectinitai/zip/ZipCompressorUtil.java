package org.dacss.projectinitai.zip;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.Objects;

/**
 * <h1>{@link ZipCompressorUtil}</h1>
 * <p>
 *     Utility class for compressing files into a zip file.
 * </p>
 */
@Component
public class ZipCompressorUtil {

    static String createZipFile() throws IOException {
        File sourceDir = new File("/path/to/source");
        File zipFile = new File("/path/to/zipfile.zip");
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            addFilesToZip(zos, sourceDir, "");
            return "Zip file created successfully";
        }
    }

    private static void addFilesToZip(ZipOutputStream zos, File file, String parent) throws IOException {
        String entryName = parent + file.getName();
        ZipEntry zipEntry = new ZipEntry(entryName);
        zos.putNextEntry(zipEntry);

        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, len);
                }
            }
            zos.closeEntry();
        } else if (file.isDirectory()) {
            zos.closeEntry();
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                addFilesToZip(zos, childFile, entryName + "/");
            }
        }
    }
}
