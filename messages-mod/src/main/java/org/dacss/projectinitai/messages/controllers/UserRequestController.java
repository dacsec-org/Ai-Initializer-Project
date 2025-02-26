package org.dacss.projectinitai.messages.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link UserRequestController}</h1>
 * UserRequestController is a class that represents the user request object.
 */
@Controller
public class UserRequestController {

    private static ChatClient chatClient;
    private static Sinks.Many<Object> userRequestSink = Sinks.many().unicast().onBackpressureBuffer();

    /**
     * <h3>{@link #UserRequestController(ChatClient)}</h3>
     * Constructor for UserRequestController taking in a ChatClient.
     * @param chatClient ChatClient
     */
    @Autowired
    public UserRequestController(ChatClient chatClient) {
        UserRequestController.chatClient = chatClient;
    }

    /**
     * <h3>{@link #sendUserRequestToLLM(Flux)}</h3>
     *
     * @param message
     * @return Flux<Object>
     */
    @MessageMapping("user.request")
    public static Flux<Object> sendUserRequestToLLM(Flux<Object> message) {
        return message
                .doOnNext(userRequestSink::tryEmitNext)
                .thenMany(userRequestSink.asFlux())
                .flatMap(msg -> chatClient.prompt(msg.toString()).stream().content().map(Object.class::cast)) // Ensure type compatibility
                .onErrorResume(e -> {
                    // Log the error and provide a fallback response
                    System.err.println("Error in sendUserRequestToLLM: " + e.getMessage());
                    return Flux.just("An error occurred while processing the user request.");
                });
    }

    /**
     * <h3>{@link #getRequestStream()}</h3>
     *
     * @return Flux<Object>
     */
    public Flux<Object> getRequestStream() {
        return userRequestSink.asFlux();
    }
}
