package org.dacss.projectinitai.snapshots;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link SnapShotsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface SnapShotsIface {

    Flux<Object> manageSnapshots(SnapShotsActions action);
}
