package org.dacss.projectinitai.security;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link SecurityIface}</h1>
 * Interface for handling security actions.
 * Provides methods to process secure actions and retrieve security-related information.
 */
@FunctionalInterface
public interface SecurityIface {

    Logger log = LoggerFactory.getLogger(SecurityIface.class);

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
            log.error("{}: Secure action failed", action, securityServiceExc);
            return Flux.empty();
        } finally {
            log.info("{}: Secure action completed", action);
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
