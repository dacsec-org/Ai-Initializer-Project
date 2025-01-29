package org.dacss.projectinitai.messages.utillities;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link TrashMessageSetUtil}</h1>
 * Utility class to destroy a message set(1 request + 1 response) from the  database.
 */
public class TrashMessageSetUtil {


    public static Flux<String> destroyMessageSet(Flux<String> messageSets) {

        return Flux.just("Message set destroyed: " + messageSets);
    }
}
