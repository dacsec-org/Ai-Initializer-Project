package org.dacss.projectinitai.advisers.utilities;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link LoggerUtil}</h1>
 * Logger utility class for the adviser logging actions.
 */
public class LoggerUtil {

    public static Flux<Object> logAction() {
        //todo: Implement the logic for logging the action. map to log dir referenced in the installer script
        return Flux.just();
    }
}
