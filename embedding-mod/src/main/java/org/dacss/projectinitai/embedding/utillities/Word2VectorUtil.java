package org.dacss.projectinitai.embedding.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link Word2VectorUtil}</h1>
 * Utility class for fetching word2vec embeddings.
 */
@Component
public class Word2VectorUtil {
    public static Flux<Object> fetchWord2Vec() {
        return Flux.just();
    }
}
