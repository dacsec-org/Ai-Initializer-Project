package org.dacss.projectinitai.models.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link CloneModelUtil}</h1>
 * Utility class for clone model operations.
 */
public class CloneModelUtil {

    public static Flux<Object> cloneModel(String modelPath) throws IOException {
        Path source = Path.of(modelPath);
        Path target = Path.of(modelPath + ".clone");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        return Flux.just((Object) "Model cloned");
    }
}
