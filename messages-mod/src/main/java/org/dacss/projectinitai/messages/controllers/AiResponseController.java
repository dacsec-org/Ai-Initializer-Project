package org.dacss.projectinitai.messages.controllers;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link AiResponseController}</h1>
 * AiResponseController is a class that represents the AI response object.
 */
@Controller
public class AiResponseController {

    private static Sinks.Many<Object> aiResponseSink = Sinks.many().unicast().onBackpressureBuffer();

    /**
     * <h3>{@link #receiveAiResponseFromLLM(Flux)}</h3>
     *
     * @param message
     * @return Flux<Object>
     */
    @MessageMapping("ai-response")
    public static Flux<Object> receiveAiResponseFromLLM(Flux<Object> message) {
        return message
                .doOnNext(aiResponseSink::tryEmitNext)
                .thenMany(aiResponseSink.asFlux())
                .onErrorResume(e -> {
                    // Log the error and provide a fallback response
                    System.err.println("Error in receiveAiResponseFromLLM: " + e.getMessage());
                    return Flux.just("An error occurred while processing the AI response.");
                });
    }

    /**
     * <h3>{@link #getResponseStream()}</h3>
     *
     * @return Flux<Object>
     */
    public Flux<Object> getResponseStream() {
        return aiResponseSink.asFlux();
    }
}
