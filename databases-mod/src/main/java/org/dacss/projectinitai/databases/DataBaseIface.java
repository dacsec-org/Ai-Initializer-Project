package org.dacss.projectinitai.databases;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DataBaseIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface DataBaseIface {

    Flux<Object>performDatabaseAction(DataBaseTypes type);
}
