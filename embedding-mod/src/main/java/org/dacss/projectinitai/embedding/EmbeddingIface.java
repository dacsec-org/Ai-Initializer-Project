package org.dacss.projectinitai.embedding;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link EmbeddingIface}</h1>
 */
@FunctionalInterface
public interface EmbeddingIface {

    Flux<Object> processEmbedding(EmbeddingTypes type);
}
