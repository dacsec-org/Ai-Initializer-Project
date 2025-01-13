package org.dacss.projectinitai.rags;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class RAGUpdater {

    public void updateRAG(String source, String destination) throws IOException {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        updateFiles(sourcePath, destinationPath);
    }

    private void updateFiles(Path sourcePath, Path destinationPath) throws IOException {
        Files.walk(sourcePath)
            .forEach(source -> {
                Path destination = destinationPath.resolve(sourcePath.relativize(source));
                try {
                    if (Files.exists(destination)) {
                        Files.delete(destination);
                    }
                    Files.copy(source, destination);
                } catch (IOException e) {
                    log.error("Failed to update file: " + source, e);
                }
            });
    }
}
