package org.dacss.projectinitai.messages.functions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link MessageHistory}</h1>
 * class to fetch message history from the data base.
 */
public class MessageHistory {

    private static Sinks.Many<Object> messageHistorySink = Sinks.many().unicast().onBackpressureBuffer();

    public static Flux<Object> getMessageHistory() {
        //TODO-> Implement the logic to fetch message history from the database
        // For now, we will just emit a sample message
        return Flux.just("Sample message history")
                   .doOnNext(messageHistorySink::tryEmitNext)
                   .thenMany(messageHistorySink.asFlux());
    }
}
