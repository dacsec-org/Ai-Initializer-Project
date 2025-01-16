package org.dacss.projectinitai.snapshots.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotCreatorUtil}</h1>
 * Utility class to create a snapshot.
 */
public class SnapShotCreatorUtil {

    private static final Logger log = LoggerFactory.getLogger(SnapShotCreatorUtil.class);

    /**
     * Creates a snapshot from a source directory to a destination directory.
     * @param source the source directory
     * @param destination the destination directory
     */
    public static void createSnapshot(String source, String destination) {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        //todo: map the source and destination to the correct path
        // referenced in the install scripts
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
