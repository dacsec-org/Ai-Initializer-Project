package org.dacss.projectinitai.messages.utillities;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link ThumbsDownUtil}</h1>
 * Utility class to handle thumbs down (saves the message as a set) to be
 * used for negative reinforcement in the data base.
 * 'thumbsdown' comes from the frontend clickable thumbs down icon.
 */
public class ThumbsDownUtil {

    public static Flux<String> processThumbsDown(Flux<String> messageSets) {

        return Flux.just("Message set saved as thumbs down: " + messageSets);

    }
}
