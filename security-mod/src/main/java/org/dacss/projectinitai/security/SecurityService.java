package org.dacss.projectinitai.security;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SecurityService}</h1>
 * Backend hilla endpoint service for security operations.
 */
@Service
@BrowserCallable
public class SecurityService {

    private SecurityHandler handler;

    /**
     * <h2>{@link #SecurityService()}</h2>
     * 0-arg constructor to instantiate the {@link SecurityHandler}.
     */
    public SecurityService() {
        this.handler = new SecurityHandler();
    }

    /**
     * <h2>{@link #handleSecurityAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleSecurityAction(String action, String data) {
        return switch (SecurityContexts.valueOf(action.toUpperCase())) {
            case PROJECT_SECURITY -> handler.handleProjectSecurity(data);
            case CYBER_SECURITY -> handler.handleCyberSecurity(data);
        };
    }
}
