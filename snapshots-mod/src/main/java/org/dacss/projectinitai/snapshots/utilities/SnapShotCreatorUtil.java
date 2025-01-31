package org.dacss.projectinitai.snapshots.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotCreatorUtil}</h1>
 * Utility class to create a snapshot.
 */
@Component
public class SnapShotCreatorUtil {

    private static final Logger log = LoggerFactory.getLogger(SnapShotCreatorUtil.class);

    public static Flux<Object> createSnapshot(String source, String destination) {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        try {
            createSnapshotDirectory(destinationPath);
            copyFiles(sourcePath, destinationPath);
            return Flux.just("Snapshot created successfully");
        } catch (IOException createSnapExc) {
            log.error("Failed to create snapshot from {} to {}", source, destination, createSnapExc);
            return Flux.error(createSnapExc);
        }
    }

    private static void createSnapshotDirectory(Path destinationPath) throws IOException {
        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }
    }

    private static void copyFiles(Path sourcePath, Path destinationPath) throws IOException {
        try (Stream<Path> paths = Files.walk(sourcePath)) {
            paths.forEach(source -> {
                Path destination = destinationPath.resolve(sourcePath.relativize(source));
                try {
                    Files.copy(source, destination);
                } catch (IOException copyFilesExc) {
                    log.error("Failed to copy file: {}", source, copyFilesExc);
                }
            });
        }
    }
}
