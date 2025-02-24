package org.dacss.projectinitai.messages.functions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link ThumbsUp}</h1>
 * class to handle thumbs up.(saves the message as a set) to be
 * used for positive reinforcement in the data base.
 * 'thumbsup' comes from the frontend clickable thumbs up icon.
 */
public class ThumbsUp {

    private static Sinks.Many<Object> thumbsUpSink = Sinks.many().unicast().onBackpressureBuffer();

    public static Flux<Object> processThumbsUp(Flux<Object> messageSets) {
        //TODO-> implement the logic to save message set to database with positive reinforcement vector
        return messageSets.doOnNext(thumbsUpSink::tryEmitNext)
                          .thenMany(thumbsUpSink.asFlux());
    }
}
