package org.dacss.projectinitai.embedding.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link BertUtil}</h1>
 * Utility class for fetching BERT embeddings.
 */
@Component
public class BertUtil {

    public static Flux<Object> fetchBert() {
        return Flux.just();
    }
}
