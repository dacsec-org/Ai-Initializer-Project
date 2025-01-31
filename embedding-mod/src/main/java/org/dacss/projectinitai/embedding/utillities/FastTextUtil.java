package org.dacss.projectinitai.embedding.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link FastTextUtil}</h1>
 * Utility class for fetching FastText embeddings.
 */
@Component
public class FastTextUtil {

    public static Flux<Object> fetchFastText() {
        return Flux.just();

    }
}
