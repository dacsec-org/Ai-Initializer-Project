package org.dacss.projectinitai.messages.functions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link RetryMessage}</h1>
 * class to retry a a users request via the retry clickable icon.
 * this will prompt the Admin to reprocess the user request, as well as the AI to reprocess the user request.
 */
public class RetryMessage {

    private static Sinks.Many<Object> retryMessageSink = Sinks.many().unicast().onBackpressureBuffer();

    public static Flux<Object> retryMessageSet(Flux<String> just) {
        //TODO-> Implement the logic to send message set to admin and AI for reprocessing
        return just.doOnNext(retryMessageSink::tryEmitNext)
                   .thenMany(retryMessageSink.asFlux());
    }
}
