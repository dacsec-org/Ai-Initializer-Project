package org.dacss.projectinitai.rags;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class RAGCreator {

    public void createRAG(String source, String destination) throws IOException {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        createRAGDirectory(destinationPath);
        copyFiles(sourcePath, destinationPath);
    }

    private void createRAGDirectory(Path destinationPath) throws IOException {
        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }
    }

    private void copyFiles(Path sourcePath, Path destinationPath) throws IOException {
        Files.walk(sourcePath)
            .forEach(source -> {
                Path destination = destinationPath.resolve(sourcePath.relativize(source));
                try {
                    Files.copy(source, destination);
                } catch (IOException e) {
                    log.error("Failed to copy file: " + source, e);
                }
            });
    }
}
