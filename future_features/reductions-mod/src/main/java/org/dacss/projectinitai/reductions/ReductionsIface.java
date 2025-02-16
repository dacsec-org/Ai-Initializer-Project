package org.dacss.projectinitai.reductions;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link ReductionsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface ReductionsIface {
    /**
     * <h2>{@link #reduceDimensions()}</h2>
     * Perform dimensionality reduction on the data.
     */
    void reduceDimensions();
}
