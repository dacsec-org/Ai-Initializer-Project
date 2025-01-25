package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.speech.SpeechIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SpeechService}</h1>
 * Backend hilla endpoint service for speech recognition operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class SpeechService implements SpeechIface {


    private static final Logger log = LoggerFactory.getLogger(SpeechService.class);

    /**
     * <h2>{@link #SpeechService()}</h2>
     */
    public SpeechService() {
    }


    /**
     * <h2>{@link #recognizeSpeech()}</h2>
     * Perform speech recognition on the data.
     */
    @Override
    public void recognizeSpeech() {

    }
}

//    /**
//     * <h2>{@link #handleSpeechAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be processed.
//     * @return The result of the action.
//     */
//    public Object handleSpeechAction(String action, String data) {
//        return switch (SpeechContexts.valueOf(action.toUpperCase())) {
//            case SPEECH_TO_TEXT -> handler.handleSpeechToText(data);
//            case TEXT_TO_SPEECH -> handler.handleTextToSpeech(data);
//            case VOICE_RECOGNITION -> handler.handleVoiceRecognition(data);
//        };
//    }
//}
