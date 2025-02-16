package org.dacss.projectinitai.messages;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link ThumbsDown}</h1>
 * class to handle thumbs down (saves the message as a set) to be
 * used for negative reinforcement in the data base.
 * 'thumbsdown' comes from the frontend clickable thumbs down icon.
 */
public class ThumbsDown {

    public static Flux<Object> processThumbsDown(Flux<Object> messageSets) {
        //todo: save message set to database with negative reinforcement
        return Flux.just("Message set saved as thumbs down: " + messageSets);

    }
}
