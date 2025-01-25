package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.recognitions.RecognitionsIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link RecognitionsService}</h1>
 * Backend hilla endpoint service for recognition operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class RecognitionsService implements RecognitionsIface {


    private static final Logger log = LoggerFactory.getLogger(RecognitionsService.class);

    /**
     * <h2>{@link #RecognitionsService()}</h2>
     */
    public RecognitionsService() {

    }

    /**
     * <h2>{@link #processRecognitions()}</h2>
     * Perform recognition on the data.
     */
    @Override
    public void processRecognitions() {

    }
}

//    /**
//     * <h2>{@link #handleRecognitionsAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be processed.
//     * @return The result of the action.
//     */
//    public Object handleRecognitionsAction(String action, String data) {
//        return switch (RecognitionsContexts.valueOf(action.toUpperCase())) {
//            case FACIAL_RECOGNITION -> handler.handleFacialRecognition(data);
//            case OBJECT_RECOGNITION -> handler.handleObjectRecognition(data);
//            case TEXT_RECOGNITION -> handler.handleTextRecognition(data);
//            case VOICE_RECOGNITION -> handler.handleVoiceRecognition(data);
//            case GESTURE_RECOGNITION -> handler.handleGestureRecognition(data);
//            case SCENE_RECOGNITION -> handler.handleSceneRecognition(data);
//        };
//    }
//}
