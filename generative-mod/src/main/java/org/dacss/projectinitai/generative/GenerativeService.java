package org.dacss.projectinitai.generative;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link GenerativeService}</h1>
 * Backend hilla endpoint service for generative AI operations.
 */
@Service
@BrowserCallable
public class GenerativeService {

    private GenerativeHandler handler;

    /**
     * <h2>{@link #GenerativeService()}</h2>
     * 0-arg constructor to instantiate the {@link GenerativeHandler}.
     */
    public GenerativeService() {
        this.handler = new GenerativeHandler();
    }

    /**
     * <h2>{@link #handleGenerativeAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleGenerativeAction(String action, String data) {
        return switch (GenerativeContexts.valueOf(action.toUpperCase())) {
            case DEEPFAKES -> handler.handleDeepfakes(data);
            case GENERATIVE_ADVERSARIAL_NETWORKS -> handler.handleGANs(data);
            case TEXT_TO_IMAGE -> handler.handleTextToImage(data);
            case VARIATIONAL_AUTOENCODERS -> handler.handleVAEs(data);
            case MUSIC_GENERATION -> handler.handleMusicGeneration(data);
            case TEXT_GENERATION -> handler.handleTextGeneration(data);
        };
    }
}
