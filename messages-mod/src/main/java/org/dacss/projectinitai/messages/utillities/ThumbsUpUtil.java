package org.dacss.projectinitai.messages.utillities;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link ThumbsUpUtil}</h1>
 * Utility class to handle thumbs up.(saves the message as a set) to be
 * used for positive reinforcement in the data base.
 * 'thumbsup' comes from the frontend clickable thumbs up icon.
 */
public class ThumbsUpUtil {


    public static Flux<String> processThumbsUp(Flux<String> messageSets) {

        return Flux.just("Message set saved as thumbs up: " + messageSets);
    }
}
