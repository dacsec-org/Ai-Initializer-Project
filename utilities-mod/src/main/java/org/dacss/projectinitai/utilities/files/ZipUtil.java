package org.dacss.projectinitai.utilities.files;
/**/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * <h1>{@link ZipUtil}</h1>
 * <p>
 * Utility class for zipping and unzipping directories.
 * </p>
 */
public class ZipUtil {

    /**
     * Zips a directory.
     *
     * @param dir     the directory to zip
     * @param zipFile the zip file
     * @throws IOException if an I/O error occurs
     */
    public static void zipDirectory(File dir, File zipFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos);
             Stream<Path> paths = Files.walk(dir.toPath())) {
            paths.filter(path -> !Files.isDirectory(path)).forEach(path -> {
                ZipEntry zipEntry = new ZipEntry(dir.toPath().relativize(path).toString());
                try {
                    zos.putNextEntry(zipEntry);
                    Files.copy(path, zos);
                    zos.closeEntry();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }

    /**
     * Unzips a directory.
     *
     * @param zipFile the zip file
     * @param destDir the destination directory
     * @throws IOException if an I/O error occurs
     */
    public static void unzipDirectory(File zipFile, File destDir) throws IOException {
        try (FileInputStream fis = new FileInputStream(zipFile);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File newFile = newDirectory(destDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException(STR."Failed to create directory \{newFile}");
                    }
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException(STR."Failed to create directory \{parent}");
                    }
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }

    /**
     * Creates a new directory.
     *
     * @param destinationDir the destination directory
     * @param zipEntry       the zip entry
     * @return the new directory
     * @throws IOException if an I/O error occurs
     */
    private static File newDirectory(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException(STR."Entry is outside of the target dir: \{zipEntry.getName()}");
        }
        return destFile;
    }
}
