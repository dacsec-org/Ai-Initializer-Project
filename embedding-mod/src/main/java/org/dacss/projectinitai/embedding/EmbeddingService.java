package org.dacss.projectinitai.embedding;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link EmbeddingService}</h1>
 * Backend hilla endpoint service for embedding operations.
 */
@Service
@BrowserCallable
public class EmbeddingService {

    private EmbeddingHandler handler;

    /**
     * <h2>{@link #EmbeddingService()}</h2>
     * 0-arg constructor to instantiate the {@link EmbeddingHandler}.
     */
    public EmbeddingService() {
        this.handler = new EmbeddingHandler();
    }

    /**
     * <h2>{@link #handleEmbeddingAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleEmbeddingAction(String action, String data) {
        return switch (EmbeddingContexts.valueOf(action.toUpperCase())) {
            case WORD2VEC -> handler.handleWord2Vec(data);
            case GLOVE -> handler.handleGloVe(data);
            case FASTTEXT -> handler.handleFastText(data);
            case BERT -> handler.handleBert(data);
            case GPT -> handler.handleGpt(data);
            case TRANSFORMER -> handler.handleTransformer(data);
        };
    }
}
