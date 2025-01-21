package org.dacss.projectinitai.models;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <h1>{@link ModelsService}</h1>
 * Backend hilla endpoint service for model operations.
 */
@Service
@BrowserCallable
public class ModelsService {

    private ModelHandler handler;

    /**
     * <h2>{@link #ModelsService()}</h2>
     * 0-arg constructor to instantiate the {@link ModelHandler}.
     */
    public ModelsService() {
        this.handler = new ModelHandler();
    }

    /**
     * <h2>{@link #handleModelAction(String, String, String)}</h2>
     * @param action The action to be performed.
     * @param modelPath1 The path of the first model.
     * @param modelPath2 The path of the second model (optional for destroy and create).
     * @return The result of the action.
     */
    public Object handleModelAction(String action, String modelPath1, String modelPath2) throws IOException {
        return switch (ModelContexts.valueOf(action.toUpperCase())) {
            case MERGE -> handler.handleMerge(modelPath1, modelPath2);
            case DESTROY -> handler.handleDestroy(modelPath1);
            case CREATE -> handler.handleCreate(modelPath1);
        };
    }
}
