package org.dacss.projectinitai.tar.utillities;
/**/
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * <h1>{@link TarCompressorUtil}</h1>
 * Utility class to create a tar file.
 */
public class TarCompressorUtil {

    /**
     * {@link #createTarFile(File, File)}
     * Method to create a tar file from a source directory.
     *
     * @param sourceDir - directory to be tarred
     * @param tarFile - tar file to be created
     * @throws IOException - if an I/O error occurs
     */
    public static void createTarFile(File sourceDir, File tarFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(tarFile);
             TarArchiveOutputStream TOS = new TarArchiveOutputStream(fos)) {
            addFilesToTar(TOS, sourceDir, "");
        }
    }

    /**
     * {@link #addFilesToTar(TarArchiveOutputStream, File, String)}
     * Method to add files to a tar file.
     *
     * @param TOS - TarArchiveOutputStream
     * @param file - File
     * @param parent - String
     * @throws IOException - if an I/O error occurs
     */
    private static void addFilesToTar(TarArchiveOutputStream TOS, File file,
                                      String parent) throws IOException {
        String entryName = parent + file.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(file, entryName);
        TOS.putArchiveEntry(tarEntry);

        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    TOS.write(buffer, 0, len);
                }
            }
            TOS.closeArchiveEntry();
        } else if (file.isDirectory()) {
            TOS.closeArchiveEntry();
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                addFilesToTar(TOS, childFile, entryName + "/");
            }
        }
    }
}
