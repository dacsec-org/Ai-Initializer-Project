package org.dacss.projectinitai.embedding.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link GloveUtil}</h1>
 * Utility class for fetching Glove embeddings.
 */
@Component
public class GloveUtil {

    public static Flux<Object> fetchGlove() {
        return Flux.just();
    }
}
