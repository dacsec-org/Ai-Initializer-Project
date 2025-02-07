package org.dacss.projectinitai.classifications;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ClassificationsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface ClassificationsIface {

    Flux<Object> classify(ClassificationsTypes type);
}
