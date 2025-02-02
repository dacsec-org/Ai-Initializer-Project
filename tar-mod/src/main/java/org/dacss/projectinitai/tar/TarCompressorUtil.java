package org.dacss.projectinitai.tar;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Component
public class TarCompressorUtil {

    static String createTarFile() throws IOException {
        File sourceDir = new File("/path/to/source");
        File tarFile = new File("/path/to/tarfile.tar");
        try (FileOutputStream fos = new FileOutputStream(tarFile);
             TarArchiveOutputStream tos = new TarArchiveOutputStream(fos)) {
            addFilesToTar(tos, sourceDir, "");
            return "Tar file created successfully";
        }
    }

    private static void addFilesToTar(TarArchiveOutputStream tos, File file, String parent) throws IOException {
        String entryName = parent + file.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(file, entryName);
        tos.putArchiveEntry(tarEntry);

        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    tos.write(buffer, 0, len);
                }
            }
            tos.closeArchiveEntry();
        } else if (file.isDirectory()) {
            tos.closeArchiveEntry();
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                addFilesToTar(tos, childFile, entryName + "/");
            }
        }
    }
}
