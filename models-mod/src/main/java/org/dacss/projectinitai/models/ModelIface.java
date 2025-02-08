package org.dacss.projectinitai.models;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import java.io.IOException;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ModelIface}</h1>
 * Functional interface for processing models.
 */
@FunctionalInterface
public interface ModelIface {

    Flux<Object> processModel(ModelActions actions, String modelPath1, String modelPath2) throws IOException;
}
