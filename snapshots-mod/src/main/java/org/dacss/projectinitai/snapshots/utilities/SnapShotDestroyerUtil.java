package org.dacss.projectinitai.snapshots.utilities;

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

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SnapShotDestroyerUtil.class);

    /**
     * Deletes a snapshot.
     * @param snapshotPath the path to the snapshot to delete
     */
    public static void deleteSnapshot(String snapshotPath) {
        Path path = Paths.get(snapshotPath);
        try {
            deleteDirectory(path);
        } catch (IOException destroySnapshotExc) {
            log.error("Failed to delete snapshot at {}", snapshotPath, destroySnapshotExc);
        }
    }

    /**
     * Deletes a directory.
     * @param path the path to the directory to delete
     * @throws IOException if an I/O error occurs
     */
    private static void deleteDirectory(Path path) throws IOException {
        if (Files.exists(path)) {
            try (Stream<Path> paths = Files.walk(path)) {
                paths.sorted(Comparator.reverseOrder())
                     .forEach(p -> {
                         try {
                             Files.delete(p);
                         } catch (IOException destroyDirectoryExc) {
                             log.error("Failed to delete file: {}", p, destroyDirectoryExc);
                         }
                     });
            }
        }
    }
}
