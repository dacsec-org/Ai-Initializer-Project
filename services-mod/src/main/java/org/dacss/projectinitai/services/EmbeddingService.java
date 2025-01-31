package org.dacss.projectinitai.services;
/**/

import org.dacss.projectinitai.embedding.EmbeddingIface;
import org.dacss.projectinitai.embedding.EmbeddingTypes;
import org.dacss.projectinitai.embedding.utillities.*;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link EmbeddingService}</h1>
 * Backend hilla endpoint service for embedding operations.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class EmbeddingService implements EmbeddingIface {

    private static final Logger log = LoggerFactory.getLogger(EmbeddingService.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

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
            log.error(RED + "Error handling operation: {}" + RESET, type, embeddingExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "Embedding operation completed: {}" + RESET, type);
        }
        return flux;
    }
}
