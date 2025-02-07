package org.dacss.projectinitai.sequence;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link SequenceIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface SequenceIface {
    /**
     * <h2>{@link #modelSequence()}</h2>
     * Perform sequence modeling on the data.
     */
    void modelSequence();
}
