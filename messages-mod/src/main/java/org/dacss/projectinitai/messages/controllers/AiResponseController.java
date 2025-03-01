package org.dacss.projectinitai.messages.controllers;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class AiResponseController {

    private static UniversalLLMClientIface llmClient;
    private static Sinks.Many<Object> aiResponseSink = Sinks.many().unicast().onBackpressureBuffer();

    @Autowired
    public AiResponseController(UniversalLLMClientIface llmClient) {
        AiResponseController.llmClient = llmClient;
    }

    @MessageMapping("ai-response")
    public static Flux<Object> receiveAiResponseFromLLM(Flux<Object> message) {
        return message
                .doOnNext(aiResponseSink::tryEmitNext)
                .thenMany(aiResponseSink.asFlux())
                .flatMap(msg -> llmClient.prompt(msg.toString()).map(Object.class::cast))
                .onErrorResume(e -> {
                    System.err.println("Error in receiveAiResponseFromLLM: " + e.getMessage());
                    return Flux.just("An error occurred while processing the AI response.");
                });
    }

    public Flux<Object> getResponseStream() {
        return aiResponseSink.asFlux();
    }
}