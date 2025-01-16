package org.dacss.projectinitai.snapshots.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotClonerUtil}</h1>
 * Utility class that clones a snapshot.
 */

public class SnapShotClonerUtil {

    private static final Logger log = LoggerFactory.getLogger(SnapShotClonerUtil.class);

    /**
     * Clones a snapshot from a source to a destination.
     * @param source the source snapshot directory
     * @param destination the destination snapshot directory
     */
    public static void copySnapshot(String source, String destination) {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        //todo: map the source and destination paths to there corresponding directories found in the install scripts
        try {
            copyFiles(sourcePath, destinationPath);
        } catch (IOException cloneSnapshotExc) {
            log.error("Failed to copy snapshot from {} to {}", source, destination, cloneSnapshotExc);
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
                //todo: map the source and destination paths to there corresponding directories
                Path destination = destinationPath.resolve(sourcePath.relativize(source));
                try {
                    Files.copy(source, destination);
                } catch (IOException fileCopyExc) {
                    log.error("Failed to copy file: {}", source, fileCopyExc);
                }
            });
        }
    }
}
