package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.vision.VisionIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link VisionService}</h1>
 * Backend hilla endpoint service for computer vision operations.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class VisionService implements VisionIface {

    private static final Logger log = LoggerFactory.getLogger(VisionService.class);

    /**
     * <h2>{@link #VisionService()}</h2>
     */
    public VisionService() {
    }


    /**
     * <h2>{@link #processInput()}</h2>
     */
    @Override
    public void processInput() {

    }
}

//    /**
//     * <h2>{@link #handleVisionAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be processed.
//     * @return The result of the action.
//     */
//    public Object handleVisionAction(String action, String data) {
//        return switch (VisionContexts.valueOf(action.toUpperCase())) {
//            case IMAGE_CLASSIFICATION -> handler.handleImageClassification(data);
//            case OBJECT_DETECTION -> handler.handleObjectDetection(data);
//            case IMAGE_SEGMENTATION -> handler.handleImageSegmentation(data);
//            case IMAGE_GENERATION -> handler.handleImageGeneration(data);
//            case IMAGE_SUPER_RESOLUTION -> handler.handleImageSuperResolution(data);
//            case IMAGE_RECOGNITION -> handler.handleImageRecognition(data);
//        };
//    }
//}
