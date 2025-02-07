package org.dacss.projectinitai.krr;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link KRRIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface KRRIface {
    /**
     * <h2>{@link #processKRR()}</h2>
     */
    void processKRR();
}
