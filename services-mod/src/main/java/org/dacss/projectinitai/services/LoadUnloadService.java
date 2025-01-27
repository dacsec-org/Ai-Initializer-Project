package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.loaders.LoadKernel;
import org.dacss.projectinitai.loaders.LoadersIface;
import org.dacss.projectinitai.loaders.UnLoadKernel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * <h1>{@link LoadUnloadService}</h1>
 * Backend hilla endpoint service for loading and unloading models.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class LoadUnloadService implements LoadersIface {

    private static final Logger log = LoggerFactory.getLogger(LoadUnloadService.class);

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
        switch (action) {
            case "load":
                //info-> these methods are not static, so we need to create an object of the class to call them.
                new LoadKernel().loadModelKernel(modelPath);
                break;
            case "unload":
                new UnLoadKernel().unloadModelKernel(modelData);
                break;
            default:
                log.error("Invalid action: {}", action);
        }
    }
}
