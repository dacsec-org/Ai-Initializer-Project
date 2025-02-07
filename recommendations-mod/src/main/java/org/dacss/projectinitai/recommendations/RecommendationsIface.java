package org.dacss.projectinitai.recommendations;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link RecommendationsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface RecommendationsIface {
    /**
     * <h2>{@link RecommendationsIface#recommend()}</h2>
     * recommend items to users.
     */
    void recommend();
}
