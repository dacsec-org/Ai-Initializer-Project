package org.dacss.projectinitai.messages;
/**/

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ThumbsUpUtil}</h1>
 * Utility class to handle thumbs up.(saves the message as a set) to be
 * used for positive reinforcement in the data base.
 * 'thumbsup' comes from the frontend clickable thumbs up icon.
 */
@Component
public class ThumbsUpUtil {


    public static Flux<Object> processThumbsUp(Flux<Object> messageSets) {
        //todo: save message set to database with positive reinforcement
        return Flux.just("Message set saved as thumbs up: " + messageSets);
    }
}
