package org.dacss.projectinitai.admin;
/**/

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.anomalies.AnomaliesIface;

/**
 * <h1>{@link AdminIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface AdminIface {

    /**
     * <h2>{@link #manage()}</h2>
     * manage the data.
     */
    void manage();

}
