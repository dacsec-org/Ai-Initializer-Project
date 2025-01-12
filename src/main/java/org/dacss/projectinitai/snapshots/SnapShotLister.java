package org.dacss.projectinitai.snapshots;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SnapShotLister {

    public SnapShotLister() {
    }

    public List<String> listSnapshots(String directory) throws IOException {
        Path dirPath = Paths.get(directory);
        return listSnapshotDirectories(dirPath);
    }

    private List<String> listSnapshotDirectories(Path dirPath) throws IOException {
        return Files.list(dirPath)
            .filter(Files::isDirectory)
            .map(Path::toString)
            .collect(Collectors.toList());
    }
}
