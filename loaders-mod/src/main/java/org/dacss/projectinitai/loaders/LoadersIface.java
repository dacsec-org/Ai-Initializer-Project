package org.dacss.projectinitai.loaders;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link LoadersIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface LoadersIface {

    Flux<Object> loadUnloadLLM(LoadUnLoadActions action);
}
