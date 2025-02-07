package org.dacss.projectinitai.recognitions;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link RecognitionsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface RecognitionsIface {
    /**
     * <h2>{@link #processRecognitions()}</h2>
     * Perform recognition on the data.
     */
    void processRecognitions();
}
