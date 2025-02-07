package org.dacss.projectinitai.reinforcement;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link ReinforcementIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface ReinforcementIface {
    /**
     * <h2>{@link #learn()}</h2>
     * Perform reinforcement learning on the data.
     */
    void learn();
}
