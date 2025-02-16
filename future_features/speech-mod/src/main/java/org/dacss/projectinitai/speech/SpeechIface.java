package org.dacss.projectinitai.speech;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link SpeechIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface SpeechIface {
    /**
     * <h2>{@link #recognizeSpeech()}</h2>
     * Perform speech recognition on the data.
     */
    void recognizeSpeech();
}
