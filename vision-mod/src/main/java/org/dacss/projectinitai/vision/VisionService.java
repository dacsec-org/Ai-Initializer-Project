package org.dacss.projectinitai.vision;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link VisionService}</h1>
 * Backend hilla endpoint service for computer vision operations.
 */
@Service
@BrowserCallable
public class VisionService {

    private VisionHandler handler;

    /**
     * <h2>{@link #VisionService()}</h2>
     * 0-arg constructor to instantiate the {@link VisionHandler}.
     */
    public VisionService() {
        this.handler = new VisionHandler();
    }

    /**
     * <h2>{@link #handleVisionAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleVisionAction(String action, String data) {
        return switch (VisionContexts.valueOf(action.toUpperCase())) {
            case IMAGE_CLASSIFICATION -> handler.handleImageClassification(data);
            case OBJECT_DETECTION -> handler.handleObjectDetection(data);
            case IMAGE_SEGMENTATION -> handler.handleImageSegmentation(data);
            case IMAGE_GENERATION -> handler.handleImageGeneration(data);
            case IMAGE_SUPER_RESOLUTION -> handler.handleImageSuperResolution(data);
            case IMAGE_RECOGNITION -> handler.handleImageRecognition(data);
        };
    }
}
