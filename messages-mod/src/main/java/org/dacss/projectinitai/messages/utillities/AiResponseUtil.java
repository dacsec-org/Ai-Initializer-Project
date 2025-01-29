package org.dacss.projectinitai.messages.utillities;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link AiResponseUtil}</h1>
 * Utility class to handle AI response messages from an LLM reactively.
 */
public class AiResponseUtil {

    /**
     * <h3>{@link #receiveAiResponseFromLLM}</h3>
     * @param message The User request message to send to the LLM.
     * @return Flux<String> - The response from the Ai LLM.
     */
    public static Flux<String> receiveAiResponseFromLLM(Flux<String> message) {
        // Simulate receiving a response from the LLM and sending it to the user
        return Flux.just("AI response: " + message);
    }
}
