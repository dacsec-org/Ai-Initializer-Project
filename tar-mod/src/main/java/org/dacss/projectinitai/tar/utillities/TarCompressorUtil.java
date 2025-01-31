package org.dacss.projectinitai.tar.utillities;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * <h1>{@link TarCompressorUtil}</h1>
 * Utility class to create a tar file.
 */
@Component
public class TarCompressorUtil {

    public static Flux<Object> createTarFile() {
        File sourceDir = new File("/path/to/source");
        File tarFile = new File("/path/to/tarfile.tar");
        try (FileOutputStream fos = new FileOutputStream(tarFile);
             TarArchiveOutputStream TOS = new TarArchiveOutputStream(fos)) {
            addFilesToTar(TOS, sourceDir, "");
            return Flux.just("Tar file created successfully");
        } catch (IOException createTarExc) {
            return Flux.error(createTarExc);
        }
    }

    private static void addFilesToTar(TarArchiveOutputStream TOS, File file, String parent) throws IOException {
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
