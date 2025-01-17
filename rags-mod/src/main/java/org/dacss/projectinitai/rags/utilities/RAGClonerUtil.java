package org.dacss.projectinitai.rags.utilities;
/**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>{@link RAGClonerUtil}</h1>
 */
public class RAGClonerUtil {

    private static final Logger log = LoggerFactory.getLogger(RAGClonerUtil.class);

    public void cloneRAG(String source, String destination) throws IOException {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        copyFiles(sourcePath, destinationPath);
    }

    private void copyFiles(Path sourcePath, Path destinationPath) throws IOException {
        Files.walk(sourcePath)
                //todo: 'Stream<Path>' used without 'try'-with-resources statement
            .forEach(source -> {
                Path destination = destinationPath.resolve(sourcePath.relativize(source));
                try {
                    Files.copy(source, destination);
                } catch (IOException cloneRagExc) {
                    log.error(STR."Failed to copy file: \{source}", cloneRagExc);
                }
            });
    }
}
