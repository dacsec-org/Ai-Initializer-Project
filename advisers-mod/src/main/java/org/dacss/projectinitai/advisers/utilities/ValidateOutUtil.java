package org.dacss.projectinitai.advisers.utilities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ValidateOutUtil}</h1>
 * Validates output data.
 */
@Component
public class ValidateOutUtil {
    public static Flux<Object> validateOutput() {
        //todo: implement
        return  Flux.just();
    }
}
