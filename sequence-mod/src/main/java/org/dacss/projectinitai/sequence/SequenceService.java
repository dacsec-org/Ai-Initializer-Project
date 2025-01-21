package org.dacss.projectinitai.sequence;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SequenceService}</h1>
 * Backend hilla endpoint service for sequence modeling operations.
 */
@Service
@BrowserCallable
public class SequenceService {

    private SequenceHandler handler;

    /**
     * <h2>{@link #SequenceService()}</h2>
     * 0-arg constructor to instantiate the {@link SequenceHandler}.
     */
    public SequenceService() {
        this.handler = new SequenceHandler();
    }

    /**
     * <h2>{@link #handleSequenceAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleSequenceAction(String action, String data) {
        return switch (SequenceContexts.valueOf(action.toUpperCase())) {
            case TIME_SERIES_FORECASTING -> handler.handleTimeSeriesForecasting(data);
            case NATURAL_LANGUAGE_PROCESSING -> handler.handleNaturalLanguageProcessing(data);
            case GENOMIC_SEQUENCE_ANALYSIS -> handler.handleGenomicSequenceAnalysis(data);
            case VIDEO_SEQUENCE_ANALYSIS -> handler.handleVideoSequenceAnalysis(data);
            case SPEECH_RECOGNITION -> handler.handleSpeechRecognition(data);
        };
    }
}
