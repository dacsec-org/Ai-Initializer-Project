package org.dacss.projectinitai.nlp;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link NLPIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface NLPIface {
    /**
     * <h2>{@link #processText(String, String)}</h2>
     * Perform NLP on the data.
     *
     * @param action The action to be performed.
     * @param data The data to be processed.
     */
    void processText(String action, String data);
}
