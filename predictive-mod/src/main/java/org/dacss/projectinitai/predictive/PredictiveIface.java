package org.dacss.projectinitai.predictive;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link PredictiveIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface PredictiveIface {
    /**
     * <h2>{@link #predict()}</h2>
     * Perform predictive analytics on the data.
     */
    void predict();
}
