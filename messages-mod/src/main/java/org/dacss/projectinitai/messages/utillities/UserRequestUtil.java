package org.dacss.projectinitai.messages.utillities;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link UserRequestUtil}</h1>
 * Utility class to handle sending user request messages to an LLM reactively.
 */
public class UserRequestUtil {

    /**
     * <h3>{@link #sendUserRequestToLLM(Flux<String>)}</h3>
     * @param message The user request message to send to the LLM.
     * @return Flux<String> - The response from the LLM.
     */
    public static Flux<String> sendUserRequestToLLM(Flux<String> message) {
        // Simulate sending a request to the LLM and receiving a response
        return Flux.just("User request received: " + message);
    }
}
