package org.dacss.projectinitai.messages.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link UserRequestUtil}</h1>
 * Utility class to handle sending user request messages to an LLM reactively.
 */
@Component
public class UserRequestUtil {

    public static Flux<Object> sendUserRequestToLLM(Flux<Object> message) {
        //todo: send message to LLM
        return Flux.just("User request received: " + message);
    }
}
