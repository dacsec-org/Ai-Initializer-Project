package org.dacss.projectinitai.messages;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link TrashMessageSet}</h1>
 * class to destroy a message set(1 request + 1 response) from the  database.
 */
public class TrashMessageSet {


    public static Flux<Object> destroyMessageSet(Flux<Object> messageSets) {
        //todo: delete message set from database
        return Flux.just("Message set destroyed: " + messageSets);
    }
}
