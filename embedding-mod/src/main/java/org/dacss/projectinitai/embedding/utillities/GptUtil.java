package org.dacss.projectinitai.embedding.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link GptUtil}</h1>
 * Utility class for fetching GPT embeddings.
 */
@Component
public class GptUtil {

    public static Flux<Object> fetchGpt() {
        return Flux.just();
    }
}
