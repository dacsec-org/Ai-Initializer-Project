package org.dacss.projectinitai.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link UserRequest}</h1>
 * UserRequest is a class that represents the user request object.
 */
@Component
public class UserRequest {

    private static Sinks.Many<Object> userRequestSink = Sinks.many().unicast().onBackpressureBuffer();
    private String from;
    private Flux<Object> userRequestFlux;

    /**
     * <h2>{@link #UserRequest(String, Flux)}</h2>
     *
     * @param from
     * @param userRequestFlux
     */
    @Autowired
    UserRequest(String from, Flux<Object> userRequestFlux) {
        this.from = from;
        this.userRequestFlux = userRequestFlux;
    }

    /**
     * <h2>{@link #sendUserRequestToLLM(Flux)}</h2>
     *
     * @param message
     * @return Flux<Object>
     */
    public static Flux<Object> sendUserRequestToLLM(Flux<Object> message) {
        return message.doOnNext(userRequestSink::tryEmitNext).thenMany(userRequestSink.asFlux());
    }

    public String getFrom() {return "SU";}

    public void setFrom(String from) {this.from = from;}

    public Flux<Object> getUserRequestFlux() {return userRequestFlux;}

    public void setUserRequestFlux(Flux<Object> userRequestFlux) {this.userRequestFlux = userRequestFlux;}
}
