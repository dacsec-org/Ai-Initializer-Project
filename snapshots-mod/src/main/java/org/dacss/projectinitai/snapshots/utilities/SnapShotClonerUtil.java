package org.dacss.projectinitai.snapshots.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

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

    public static Flux<Object> copySnapshot(String source, String destination) {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        try {
            copyFiles(sourcePath, destinationPath);
            return Flux.just("Snapshot copied successfully");
        } catch (IOException cloneSnapshotExc) {
            log.error("Failed to copy snapshot from {} to {}", source, destination, cloneSnapshotExc);
            return Flux.error(cloneSnapshotExc);
        }
    }

    private static void copyFiles(Path sourcePath, Path destinationPath) throws IOException {
        try (Stream<Path> paths = Files.walk(sourcePath)) {
            paths.forEach(source -> {
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
