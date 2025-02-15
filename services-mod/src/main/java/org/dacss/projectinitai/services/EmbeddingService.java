package org.dacss.projectinitai.services;

import org.dacss.projectinitai.annotations.Bridge;
import org.dacss.projectinitai.embedding.EmbeddingIface;
import org.dacss.projectinitai.embedding.EmbeddingTypes;
import org.dacss.projectinitai.embedding.utillities.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link EmbeddingService}</h1>
 * Backend endpoint service for embedding operations.
 */
@Service
@Bridge("embedding-service")
public class EmbeddingService implements EmbeddingIface {

    private static final Logger log = LoggerFactory.getLogger(EmbeddingService.class);

    @Override
    public Flux<Object> processEmbedding(EmbeddingTypes type) {
        Flux<Object> flux;
        try {
            flux = switch (type) {
                case WORD2VEC -> Word2VectorUtil.fetchWord2Vec();
                case GLOVE -> GloveUtil.fetchGlove();
                case FASTTEXT -> FastTextUtil.fetchFastText();
                case BERT -> BertUtil.fetchBert();
                case GPT -> GptUtil.fetchGpt();
                case TRANSFORMER -> TransformerUtil.fetchTransformer();
            };
        } catch (Exception embeddingExc) {
            log.error("{}: Error handling operation: {}", embeddingExc, type);
            return Flux.empty();
        } finally {
            log.info("{}: Embedding operation completed:", type);
        }
        return flux;
    }
}
