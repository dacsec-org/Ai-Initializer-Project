package org.dacss.projectinitai.optimizations;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link OptimizationsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface OptimizationsIface {
    /**
     * <h2>{@link #optimize(String, String)}</h2>
     * Perform optimization on the data.
     *
     * @param action The optimization action to perform.
     * @param data The data to optimize.
     */
    void optimize(String action, String data);
}
