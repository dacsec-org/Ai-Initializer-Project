package org.dacss.projectinitai.tar.utillities;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <h1>{@link TarExtractorUtil}</h1>
 * Utility class to extract a tar file.
 */
@Component
public class TarExtractorUtil {

    public static Flux<Object> extractTarFile() {
        File tarFile = new File("/path/to/tarfile.tar");
        File destDir = new File("/path/to/destination");
        try (FileInputStream FIS = new FileInputStream(tarFile);
             TarArchiveInputStream TAIS = new TarArchiveInputStream(FIS)) {
            TarArchiveEntry entry;
            while ((entry = TAIS.getNextEntry()) != null) {
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
                        while ((len = TAIS.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
            return Flux.just("Tar file extracted successfully");
        } catch (IOException extractTarExc) {
            return Flux.error(extractTarExc);
        }
    }
}
