package org.dacss.projectinitai.tar.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>{@link TarDestroyUtil}</h1>
 * Utility class to destroy a tar file.
 */
@Component
public class TarDestroyUtil {


    public static Flux<Object> destroyTarFile() {
        File tarFile = new File("/path/to/tarfile.tar");
        File destDir = new File("/path/to/destination");
        if (verifyExtraction(destDir)) {
            try {
                if (Files.deleteIfExists(tarFile.toPath())) {
                    return Flux.just("Tar file deleted: " + tarFile.getAbsolutePath());
                } else {
                    return Flux.just("Failed to delete tar file: " + tarFile.getAbsolutePath());
                }
            } catch (IOException destroyExc) {
                return Flux.error(destroyExc);
            }
        } else {
            return Flux.just("Extraction verification failed for directory: " + destDir.getAbsolutePath());
        }
    }

    private static boolean verifyExtraction(File destDir) {
        if (!destDir.exists() || !destDir.isDirectory()) {
            return false;
        }
        String[] files = destDir.list();
        return files != null && files.length > 0;
    }
}
