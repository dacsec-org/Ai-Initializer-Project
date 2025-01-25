package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.embedding.EmbeddingIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link EmbeddingService}</h1>
 * Backend hilla endpoint service for embedding operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class EmbeddingService implements EmbeddingIface {


    private static final Logger log = LoggerFactory.getLogger(EmbeddingService.class);

    /**
     * <h2>{@link #EmbeddingService()}</h2>
     */
    public EmbeddingService() {
    }

    /**
     * <h2>{@link #processEmbedding()}</h2>
     */
    @Override
    public void processEmbedding() {

    }
}

//    /**
//     * <h2>{@link #handleEmbeddingAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be processed.
//     * @return The result of the action.
//     */
//    public Object handleEmbeddingAction(String action, String data) {
//        return switch (EmbeddingContexts.valueOf(action.toUpperCase())) {
//            case WORD2VEC -> handler.handleWord2Vec(data);
//            case GLOVE -> handler.handleGloVe(data);
//            case FASTTEXT -> handler.handleFastText(data);
//            case BERT -> handler.handleBert(data);
//            case GPT -> handler.handleGpt(data);
//            case TRANSFORMER -> handler.handleTransformer(data);
//        };
//    }
//}
