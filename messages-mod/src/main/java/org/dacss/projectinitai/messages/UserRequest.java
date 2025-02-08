package org.dacss.projectinitai.messages;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link UserRequest}</h1>
 * UserRequest is a class that represents the user request object.
 */
public class UserRequest {

    private static Sinks.Many<Object> userRequestSink = Sinks.many().unicast().onBackpressureBuffer();

    /**
     * <h3>{@link #sendUserRequestToLLM(Flux)}</h3>
     *
     * @param message
     * @return Flux<Object>
     */
    public static Flux<Object> sendUserRequestToLLM(Flux<Object> message) {
        return message.doOnNext(userRequestSink::tryEmitNext).thenMany(userRequestSink.asFlux());
    }

    /**
     * <h3>{@link #getRequestStream()}</h3>
     *
     * @return Flux<Object>
     */
    public static Flux<Object> getRequestStream() {
        return userRequestSink.asFlux();
    }
}
