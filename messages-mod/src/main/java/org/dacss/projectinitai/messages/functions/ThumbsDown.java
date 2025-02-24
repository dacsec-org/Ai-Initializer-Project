package org.dacss.projectinitai.messages.functions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link ThumbsDown}</h1>
 * class to handle thumbs down (saves the message as a set) to be
 * used for negative reinforcement in the data base.
 * 'thumbsdown' comes from the frontend clickable thumbs down icon.
 */
public class ThumbsDown {

    private static Sinks.Many<Object> thumbsDownSink = Sinks.many().unicast().onBackpressureBuffer();

    public static Flux<Object> processThumbsDown(Flux<Object> messageSets) {
        //TODO-> Implement the logic to save message set to database with negative reinforcement
        return messageSets.doOnNext(thumbsDownSink::tryEmitNext)
                          .thenMany(thumbsDownSink.asFlux());
    }
}
