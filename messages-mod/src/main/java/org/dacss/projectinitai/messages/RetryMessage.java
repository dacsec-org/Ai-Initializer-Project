package org.dacss.projectinitai.messages;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link RetryMessage}</h1>
 * class to retry a a users request via the retry clickable icon.
 * this will prompt the Admin to reprocess the user request, as well as the AI to reprocess the user request.
 */
public class RetryMessage {

    public static Flux<Object> retryMessageSet(Flux<String> just) {
        //todo: send message set to admin and AI for reprocessing
        return Flux.just(new Object());
    }
}
