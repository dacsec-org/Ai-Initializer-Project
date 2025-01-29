package org.dacss.projectinitai.messages.utillities;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link RetryMessageUtil}</h1>
 * Utility class to retry a a users request via the retry clickable icon.
 * this will prompt the Admin to reprocess the user request, as well as the AI to reprocess the user request.
 */
public class RetryMessageUtil {


    public static Flux<String> processRetryMessage(Flux<String> messageSets) {

        return Flux.just("Message set retried: " + messageSets);
    }
}
