package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.embedding.EmbeddingIface;
import org.dacss.projectinitai.embedding.utillities.*;
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
     * 0-arg constructor.
     */
    public EmbeddingService() {
    }

    /**
     * <h2>{@link #processEmbedding(String action, String data)}</h2>
     * Perform embedding operations. via the functional interface {@link EmbeddingIface}.
     *
     * @param action The action to perform.
     * @param data The data to process.
     */
    @Override
    public void processEmbedding(String action, String data) {
        try {
            switch (action) {
                case "word2vec":
                    Word2VectorUtil.useWord2VecUtil(data);
                    break;
                case "glove":
                    GloveUtil.useGloveUtil(data);
                    break;
                case "fasttext":
                    FastTextUtil.useFastTextUtil(data);
                    break;
                case "bert":
                    BertUtil.useBertUtil(data);
                    break;
                case "gpt":
                    GptUtil.useGptUtil(data);
                    break;
                case "transformer":
                    TransformerUtil.useTransformerUtil(data);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid action: " + action);
            }
        } catch (Exception embeddingExc) {
            log.error("Error handling operation: {}", action, embeddingExc);
        }

    }
}
