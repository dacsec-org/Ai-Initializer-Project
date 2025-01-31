package org.dacss.projectinitai.messages.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link AiResponseUtil}</h1>
 * Utility class to handle AI response messages from an LLM reactively.
 */
@Component
public class AiResponseUtil {

    public static Flux<Object> receiveAiResponseFromLLM(Flux<Object> message) {
        //todo: receive message from LLM
        return Flux.just("AI response: " + message);
    }
}
