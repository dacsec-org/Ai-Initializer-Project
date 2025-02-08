package org.dacss.projectinitai.messages;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link AiResponse}</h1>
 * AiResponse is a class that represents the AI response object.
 */
public class AiResponse {

    private static Sinks.Many<Object> aiResponseSink = Sinks.many().unicast().onBackpressureBuffer();

    /**
     * <h3>{@link #receiveAiResponseFromLLM(Flux)}</h3>
     *
     * @param message
     * @return Flux<Object>
     */
    public static Flux<Object> receiveAiResponseFromLLM(Flux<Object> message) {
        return message.doOnNext(aiResponseSink::tryEmitNext).thenMany(aiResponseSink.asFlux());
    }
}
