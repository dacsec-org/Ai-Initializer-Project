package org.dacss.projectinitai.messages.functions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link ChatsList}</h1>
 * class to return a list of chats.
 */
public class ChatsList {

    private static Sinks.Many<Object> chatsListSink = Sinks.many().unicast().onBackpressureBuffer();

    public static Flux<Object> getChatsList() {
        //TODO-> Implement the logic to fetch the list of chats
        // For now, we will just emit a sample chat list
        return Flux.just("Sample chat list")
                   .doOnNext(chatsListSink::tryEmitNext)
                   .thenMany(chatsListSink.asFlux());
    }
}
