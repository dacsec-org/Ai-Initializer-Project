package org.dacss.projectinitai.utillities;

import lombok.experimental.UtilityClass;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * <h1>{@link CompressorUtil}</h1>
 * Utility class to create a tar file.
 */
@UtilityClass
public class CompressorUtil {

    public void createTarFile(File sourceDir, File tarFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(tarFile);
             TarArchiveOutputStream taos = new TarArchiveOutputStream(fos)) {
            addFilesToTar(taos, sourceDir, "");
        }
    }

    private void addFilesToTar(TarArchiveOutputStream taos, File file, String parent) throws IOException {
        String entryName = parent + file.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(file, entryName);
        taos.putArchiveEntry(tarEntry);

        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    taos.write(buffer, 0, len);
                }
            }
            taos.closeArchiveEntry();
        } else if (file.isDirectory()) {
            taos.closeArchiveEntry();
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                addFilesToTar(taos, childFile, entryName + "/");
            }
        }
    }
}
