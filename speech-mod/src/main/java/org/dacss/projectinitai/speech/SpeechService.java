package org.dacss.projectinitai.speech;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SpeechService}</h1>
 * Backend hilla endpoint service for speech recognition operations.
 */
@Service
@BrowserCallable
public class SpeechService {

    private SpeechHandler handler;

    /**
     * <h2>{@link #SpeechService()}</h2>
     * 0-arg constructor to instantiate the {@link SpeechHandler}.
     */
    public SpeechService() {
        this.handler = new SpeechHandler();
    }

    /**
     * <h2>{@link #handleSpeechAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleSpeechAction(String action, String data) {
        return switch (SpeechContexts.valueOf(action.toUpperCase())) {
            case SPEECH_TO_TEXT -> handler.handleSpeechToText(data);
            case TEXT_TO_SPEECH -> handler.handleTextToSpeech(data);
            case VOICE_RECOGNITION -> handler.handleVoiceRecognition(data);
        };
    }
}
