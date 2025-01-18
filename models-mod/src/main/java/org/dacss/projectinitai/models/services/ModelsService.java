package org.dacss.projectinitai.models.services;
/**/
import org.dacss.projectinitai.models.handlers.ModelHandler;
/**/
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <h1>{@link ModelsService}</h1>
 * <p>
 * Hilla endpoint for merging and destroying models via the {@link ModelHandler}.
 * </p>
 */
@Service
@BrowserCallable
public class ModelsService {

    private final ModelHandler handler;

    /**
     * {@link #ModelsService(ModelHandler)}
     *
     * @param handler
     */
    public ModelsService(ModelHandler handler) {
        this.handler = handler;
    }

    /**
     * {@link #handleModelAction(String, String, String)}
     * <p>
     * Switch to handle the model actions.
     * </p>
     * @param action     The action to be performed (merge or destroy).
     * @param modelPath1 The path of the first model.
     * @param modelPath2 The path of the second model (optional for destroy).
     */
    private void handleModelAction(String action, String modelPath1, String modelPath2) throws IOException {
        switch (action) {
            case "merge":
                handler.mergeModels(modelPath1, modelPath2);
                break;
            case "destroy":
                handler.destroyModel(modelPath1);
                break;
            default:
                throw new IllegalArgumentException(STR."Unknown action: \{action}");
        }
    }
}
