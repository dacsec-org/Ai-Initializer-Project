package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.loaders.LoadKernel;
import org.dacss.projectinitai.loaders.LoadUnLoadActions;
import org.dacss.projectinitai.loaders.LoadersIface;
import org.dacss.projectinitai.loaders.UnLoadKernel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link LoadUnloadService}</h1>
 * Backend hilla endpoint service for loading and unloading models.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class LoadUnloadService implements LoadersIface {

    private static final Logger log = LoggerFactory.getLogger(LoadUnloadService.class);

    public LoadUnloadService() {}


    @Override
    public Flux<Object> loadUnloadLLM(LoadUnLoadActions action) {
        switch (action) {
            case LOAD_KERNEL:
                String modelPath = null;
                new LoadKernel().loadModelKernel(modelPath);
                break;
            case UNLOAD_KERNEL:
                byte[] modelData = new byte[0];
                new UnLoadKernel().unloadModelKernel(modelData);
                break;
        }
        return Flux.just(MessageFormat.format("Model {0} operation completed", action));
    }
}
