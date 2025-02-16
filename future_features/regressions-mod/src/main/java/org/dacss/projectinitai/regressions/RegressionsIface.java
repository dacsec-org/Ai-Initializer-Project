package org.dacss.projectinitai.regressions;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link RegressionsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface RegressionsIface {
    /**
     * <h2>{@link #regress()}</h2>
     * Perform regression on the data.
     */
    void regress();
}
