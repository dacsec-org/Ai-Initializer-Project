package org.dacss.projectinitai.snapshots.utilities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>{@link SnapShotListerUtil}</h1>
 * Utility class that lists snapshots and directories.
 */
@Component
public class SnapShotListerUtil {

    public static Flux<Object> listSnapshots(String directory) {
        Path dirPath = Paths.get(directory);
        try {
            return listSnapshotDirectories(String.valueOf(dirPath));
        } catch (IOException listSnapshotsExc) {
            return Flux.error(listSnapshotsExc);
        }
    }

    public static Flux<Object> listSnapshotDirectories(String dirPath) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            List<String> directories = paths.filter(Files::isDirectory)
                                            .map(Path::toString)
                                            .collect(Collectors.toList());
            return Flux.just(directories);
        }
    }
}
