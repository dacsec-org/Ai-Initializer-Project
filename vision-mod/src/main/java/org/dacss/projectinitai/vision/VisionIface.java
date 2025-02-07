package org.dacss.projectinitai.vision;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link VisionIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface VisionIface {
    /**
     * <h2>{@link #processInput()}</h2>
     * Perform computer vision on the data.
     */
    void processInput();
}
