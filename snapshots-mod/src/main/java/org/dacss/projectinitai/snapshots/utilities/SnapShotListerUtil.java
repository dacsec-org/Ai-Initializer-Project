package org.dacss.projectinitai.snapshots.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotListerUtil}</h1>
 * Utility class that lists snapshots in a directory.
 */
public class SnapShotListerUtil {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SnapShotListerUtil.class);

    /**
     * Lists snapshots in a directory.
     * @param directory the directory to list snapshots from
     * @return a list of snapshot directories
     */
    public static List<String> listSnapshots(String directory) {
        Path dirPath = Paths.get(directory);
        try {
            return listSnapshotDirectories(dirPath);
        } catch (IOException listSnapshotsExc) {
            log.error("Failed to list snapshots in directory {}", directory, listSnapshotsExc);
            return List.of();
        }
    }

    /**
     * Lists snapshot directories in a directory.
     * @param dirPath the directory to list snapshot directories from
     * @return a list of snapshot directories
     * @throws IOException if an I/O error occurs
     */
    private List<String> listSnapshotDirectories(Path dirPath) throws IOException {
        try (Stream<Path> paths = Files.list(dirPath)) {
            return paths
                .filter(Files::isDirectory)
                .map(Path::toString)
                .collect(Collectors.toList());
        }
    }
}
