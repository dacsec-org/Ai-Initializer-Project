package org.dacss.projectinitai.recognitions;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link RecognitionsService}</h1>
 * Backend hilla endpoint service for recognition operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class RecognitionsService {

    private RecognitionsHandler handler;

    /**
     * <h2>{@link #RecognitionsService()}</h2>
     * 0-arg constructor to instantiate the {@link RecognitionsHandler}.
     */
    public RecognitionsService() {
        this.handler = new RecognitionsHandler();
    }

    /**
     * <h2>{@link #handleRecognitionsAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleRecognitionsAction(String action, String data) {
        return switch (RecognitionsContexts.valueOf(action.toUpperCase())) {
            case FACIAL_RECOGNITION -> handler.handleFacialRecognition(data);
            case OBJECT_RECOGNITION -> handler.handleObjectRecognition(data);
            case TEXT_RECOGNITION -> handler.handleTextRecognition(data);
            case VOICE_RECOGNITION -> handler.handleVoiceRecognition(data);
            case GESTURE_RECOGNITION -> handler.handleGestureRecognition(data);
            case SCENE_RECOGNITION -> handler.handleSceneRecognition(data);
        };
    }
}
