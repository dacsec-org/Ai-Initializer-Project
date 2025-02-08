package org.dacss.projectinitai.models.utilities;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ListModelsUtil}</h1>
 * Utility class for listing models in a non-blocking way.
 */
public class ListModelsUtil {

    private static final Path LLM_DIRECTORY = Path.of("/home/$USER/.ai-initializer-project/models");

    /**
     * <h3>{@link #ListModelsUtil()}</h3>
     * Private constructor to prevent instantiation.
     */
    private ListModelsUtil() {}

    /**
     * <h3>{@link #listModels()}</h3>
     * Lists all models in the models directory.
     * @return A Flux of all models in the models directory.
     */
    public static Flux<Object> listModels() {
        File[] files = LLM_DIRECTORY.toFile().listFiles();
        if (files == null || files.length == 0) {
            return Flux.empty();
        }
        return Flux.using(
            () -> Stream.of(Objects.requireNonNull(files)),
            Flux::fromStream,
            Stream::close
        ).map(File::getName);
    }
}
