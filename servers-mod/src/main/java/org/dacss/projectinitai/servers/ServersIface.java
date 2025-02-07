package org.dacss.projectinitai.servers;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link ServersIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface ServersIface {
    /**
     * <h2>{@link #manageServer()}</h2>
     * Perform server management operations.
     */
    void manageServer(String operation);
}
