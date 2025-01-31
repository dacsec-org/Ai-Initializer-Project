package org.dacss.projectinitai.embedding.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link TransformerUtil}</h1>
 * Utility class for fetching transformer embeddings.
 */
@Component
public class TransformerUtil {

    public static Flux<Object> fetchTransformer() {
        return Flux.just();
    }
}
