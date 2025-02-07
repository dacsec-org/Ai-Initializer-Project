package org.dacss.projectinitai.models.utilities;

import reactor.core.publisher.Flux;
import java.io.File;

/**
 * <h1>{@link DestroyModelUtil}</h1>
 * Utility class for deleting local models.
 */
public class DestroyModelUtil {

    public static Flux<Object> destroyModel(String modelPath) {
        File modelFile = new File(modelPath);
        if (modelFile.exists()) {
            boolean deleted = modelFile.delete();
            return Flux.just(deleted ? "Model deleted" : "Failed to delete model");
        } else {
            return Flux.just("Model does not exist: " + modelPath);
        }
    }
}
