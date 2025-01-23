package org.dacss.projectinitai.loaders;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <h1>{@link LoadUnloadService}</h1>
 * Backend hilla endpoint service for loading and unloading models.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class LoadUnloadService implements LoadersIface {

    /**
     * <h2>{@link #LoadUnloadService()}</h2>
     * 0-arg constructor.
     */
    public LoadUnloadService() {}

    /**
     * <h2>{@link #loadUnloadLLM(String, String, byte[])}</h2>
     * Perform load/unload operations on models.
     *
     * @param action    The action to perform on the model.
     * @param modelPath The path to the model.
     * @param modelData The model data.
     */
    @Override
    public void loadUnloadLLM(String action, String modelPath, byte[] modelData) {
        switch (action.toLowerCase()) {
            case "load":
                new LoadKernel().loadModelKernel(modelPath);
                break;
            case "unload":
                new UnLoadKernel().unloadModelKernel(modelData);
                break;
            default:
                throw new IllegalArgumentException(STR."Unknown action: \{action}");
        }
    }
}
