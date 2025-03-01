package org.dacss.projectinitai.messages.controllers;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class UserRequestController {

    private static UniversalLLMClientIface llmClient;
    private static Sinks.Many<Object> userRequestSink = Sinks.many().unicast().onBackpressureBuffer();

    @Autowired
    public UserRequestController(UniversalLLMClientIface llmClient) {
        UserRequestController.llmClient = llmClient;
    }

    @MessageMapping("user.request")
    public static Flux<Object> sendUserRequestToLLM(Flux<Object> message) {
        return message
                .doOnNext(userRequestSink::tryEmitNext)
                .thenMany(userRequestSink.asFlux())
                .flatMap(msg -> llmClient.prompt(msg.toString()).map(Object.class::cast))
                .onErrorResume(e -> {
                    System.err.println("Error in sendUserRequestToLLM: " + e.getMessage());
                    return Flux.just("An error occurred while processing the user request.");
                });
    }

    public Flux<Object> getRequestStream() {
        return userRequestSink.asFlux();
    }
}