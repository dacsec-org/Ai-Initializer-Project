package org.dacss.projectinitai.loaders;

import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.loaders.LoadUnloadHandler;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link LoadUnloadService}</h1>
 * Backend hilla endpoint service for loading and unloading models.
 */
@Service
@BrowserCallable
public class LoadUnloadService {

    private LoadUnloadHandler handler;

    /**
     * <h2>{@link #LoadUnloadService()}</h2>
     * 0-arg constructor to instantiate the {@link LoadUnloadHandler}.
     */
    public LoadUnloadService() {
        this.handler = new LoadUnloadHandler();
    }

    /**
     * <h2>{@link #handleModelAction(String, String, byte[])}</h2>
     * @param action The action to be performed.
     * @param modelPath The path to the model.
     * @param modelData The model data.
     * @return The result of the action.
     */
    public Object handleModelAction(String action, String modelPath, byte[] modelData) {
        return switch (action.toLowerCase()) {
            case "load" -> handler.loadModel(modelPath);
            case "unload" -> handler.unloadModel(modelData);
            default -> throw new IllegalArgumentException(STR."Unknown action: \{action}");
        };
    }
}
