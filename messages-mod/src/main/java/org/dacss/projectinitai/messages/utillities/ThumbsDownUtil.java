package org.dacss.projectinitai.messages.utillities;
/**/

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ThumbsDownUtil}</h1>
 * Utility class to handle thumbs down (saves the message as a set) to be
 * used for negative reinforcement in the data base.
 * 'thumbsdown' comes from the frontend clickable thumbs down icon.
 */
@Component
public class ThumbsDownUtil {

    public static Flux<Object> processThumbsDown(Flux<Object> messageSets) {
        //todo: save message set to database with negative reinforcement
        return Flux.just("Message set saved as thumbs down: " + messageSets);

    }
}
