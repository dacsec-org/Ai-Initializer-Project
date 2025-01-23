package org.dacss.projectinitai.nlp;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link NLPService}</h1>
 * Backend hilla endpoint service for NLP operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class NLPService {

    private NLPHandler handler;

    /**
     * <h2>{@link #NLPService()}</h2>
     * 0-arg constructor to instantiate the {@link NLPHandler}.
     */
    public NLPService() {
        this.handler = new NLPHandler();
    }

    /**
     * <h2>{@link #handleNLPAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleNLPAction(String action, String data) {
        return switch (NLPContexts.valueOf(action.toUpperCase())) {
            case TEXT_GENERATION -> handler.handleTextGeneration(data);
            case SENTIMENT_ANALYSIS -> handler.handleSentimentAnalysis(data);
            case NAMED_ENTITY_RECOGNITION -> handler.handleNamedEntityRecognition(data);
            case MACHINE_TRANSLATION -> handler.handleMachineTranslation(data);
            case TEXT_SUMMARIZATION -> handler.handleTextSummarization(data);
        };
    }
}
