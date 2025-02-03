package org.dacss.projectinitai.security;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link SecurityIface}</h1>
 * Interface for handling security actions.
 * Provides methods to process secure actions and retrieve security-related information.
 */
@Endpoint
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface SecurityIface {

    Logger log = LoggerFactory.getLogger(SecurityIface.class);
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    /**
     * <h3>{@link #processSecureAction(SecurityActions)}</h3>
     * Processes a secure action based on the provided {@link SecurityActions}.
     * The method performs the following steps:
     * <ul>
     *     <li>Determines the action to be performed.</li>
     *     <li>Calls the appropriate utility method to handle the action.</li>
     *     <li>Logs the completion of the action.</li>
     * </ul>
     *
     * @param action The security action to be performed.
     * @return a Flux containing the result of the security action.
     * @throws IOException if there is an error performing the security action.
     */
    static Flux<Object> processSecureAction(SecurityActions action) {
        Flux<Object> result;
        try {
            result = switch (action) {
                case API_TOKEN -> SecurityApiTokenUtil.getApiToken();
                case PROJECT_SECURITY, CYBER_SECURITY -> null;
            };
        } catch (Exception securityServiceExc) {
            log.error(RED + "Error from SecurityService performing action: {}" + RESET, action, securityServiceExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "SecurityService action completed: {}" + RESET, action);
        }
        assert result != null;
        return result;
    }

    /**
     * <h2>{@link #secure(SecurityActions)}</h2>
     * Performs a secure action based on the provided {@link SecurityActions}.
     *
     * @param action The security action to be performed.
     * @return a Flux containing the result of the security action.
     * @throws IOException if there is an error performing the security action.
     */
    Flux<Object> secure(SecurityActions action) throws IOException;
}
