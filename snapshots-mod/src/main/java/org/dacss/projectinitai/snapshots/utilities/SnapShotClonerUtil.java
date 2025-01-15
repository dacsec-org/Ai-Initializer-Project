// SnapShotClonerUtil.java
package org.dacss.projectinitai.snapshots.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotClonerUtil}</h1>
 * Utility class that clones a snapshot.
 */
@Slf4j
@UtilityClass
public class SnapShotClonerUtil {

    /**
     * Clones a snapshot from a source to a destination.
     * @param source the source snapshot directory
     * @param destination the destination snapshot directory
     */
    public void copySnapshot(String source, String destination) {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        try {
            copyFiles(sourcePath, destinationPath);
        } catch (IOException e) {
            log.error("Failed to copy snapshot from {} to {}", source, destination, e);
        }
    }

    /**
     * Copies files from a source directory to a destination directory.
     * @param sourcePath the source directory
     * @param destinationPath the destination directory
     * @throws IOException if an I/O error occurs
     */
    private void copyFiles(Path sourcePath, Path destinationPath) throws IOException {
        try (Stream<Path> paths = Files.walk(sourcePath)) {
            paths.forEach(source -> {
                Path destination = destinationPath.resolve(sourcePath.relativize(source));
                try {
                    Files.copy(source, destination);
                } catch (IOException e) {
                    log.error("Failed to copy file: {}", source, e);
                }
            });
        }
    }
}
