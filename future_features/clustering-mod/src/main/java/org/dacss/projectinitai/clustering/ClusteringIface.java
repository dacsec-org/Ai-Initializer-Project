package org.dacss.projectinitai.clustering;
/**/

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link ClusteringIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface ClusteringIface {
    /**
     * <h2>{@link #performClustering()}</h2>
     */
    void performClustering();
}
