package org.dacss.projectinitai.generative;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link GenerativeIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface GenerativeIface {
    /**
     * <h2>{@link #processGenerative()}</h2>
     */
    void processGenerative();
}
