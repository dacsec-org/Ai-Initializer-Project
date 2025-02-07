package org.dacss.projectinitai.snapshots.utilities;

import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotDestroyerUtil}</h1>
 * Utility class that deletes snapshots.
 */
public class SnapShotDestroyerUtil {

    public static Flux<Object> deleteSnapshot(String snapshotPath) {
        Path path = Paths.get(snapshotPath);
        try {
            return deleteDirectory(path);
        } catch (IOException deleteSnapshotExc) {
            return Flux.error(deleteSnapshotExc);
        }
    }

    public static Flux<Object> deleteDirectory(Path path) throws IOException {
        if (Files.exists(path)) {
            try (Stream<Path> paths = Files.walk(path)) {
                paths.sorted(Comparator.reverseOrder())
                     .map(Path::toFile)
                     .forEach(File::delete);
            }
        }
        return Flux.just("Snapshot deleted successfully");
    }
}
