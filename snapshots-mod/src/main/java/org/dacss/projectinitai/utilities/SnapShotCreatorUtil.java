// SnapShotCreatorUtil.java
package org.dacss.projectinitai.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotCreatorUtil}</h1>
 * Utility class to create a snapshot.
 */
@Slf4j
@UtilityClass
public class SnapShotCreatorUtil {

    /**
     * Creates a snapshot from a source directory to a destination directory.
     * @param source the source directory
     * @param destination the destination directory
     */
    public void createSnapshot(String source, String destination) {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        try {
            createSnapshotDirectory(destinationPath);
            copyFiles(sourcePath, destinationPath);
        } catch (IOException e) {
            log.error("Failed to create snapshot from {} to {}", source, destination, e);
        }
    }

    /**
     * Creates a snapshot directory if it does not exist.
     * @param destinationPath the destination directory path
     * @throws IOException if an I/O error occurs
     */
    private void createSnapshotDirectory(Path destinationPath) throws IOException {
        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }
    }

    /**
     * Copies files from a source directory to a destination directory.
     * @param sourcePath the source directory path
     * @param destinationPath the destination directory path
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
