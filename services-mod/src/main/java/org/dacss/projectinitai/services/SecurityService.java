package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.security.SecurityIface;
import org.dacss.projectinitai.security.utilities.SecurityApiTokenUtil;
import com.vaadin.hilla.BrowserCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <h1>{@link SecurityService}</h1>
 * Backend hilla endpoint service for security operations.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class SecurityService implements SecurityIface {

    private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

    /**
     * <h2>{@link #SecurityService()}</h2>
     * 0-arg constructor.
     */
    public SecurityService() {}

    /**
     * <h2>{@link SecurityIface#secure()}</h2>
     * Perform security operations on the data via the function interface {@link SecurityIface}.
     */
    @Override
    public void secure() {
        try {
            SecurityApiTokenUtil.getApiToken();
        } catch (IOException secExc) {
            log.error("Error setting API token", secExc);
        }
    }
}
