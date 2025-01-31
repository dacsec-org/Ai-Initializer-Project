package org.dacss.projectinitai.messages.utillities;
/**/

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link TrashMessageSetUtil}</h1>
 * Utility class to destroy a message set(1 request + 1 response) from the  database.
 */
@Component
public class TrashMessageSetUtil {


    public static Flux<Object> destroyMessageSet(Flux<Object> messageSets) {
        //todo: delete message set from database
        return Flux.just("Message set destroyed: " + messageSets);
    }
}
