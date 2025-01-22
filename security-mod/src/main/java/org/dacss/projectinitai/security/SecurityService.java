package org.dacss.projectinitai.security;

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
@BrowserCallable
public class SecurityService implements SecurityIface {

    private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

    /**
     * <h2>{@link #SecurityService()}</h2>
     * 0-arg constructor.
     */
    public SecurityService() {}

    /**
     * <h2>{@link SecurityIface#secure()}</h2>
     * Perform security operations on the data.
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
