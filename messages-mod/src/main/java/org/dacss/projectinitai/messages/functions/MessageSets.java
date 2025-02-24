package org.dacss.projectinitai.messages.functions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link MessageSets}</h1>
 * Represents 1 user request and its AI response as 1 'set' of messages.
 */
public class MessageSets {

    private static Sinks.Many<Object> messageSetsSink = Sinks.many().unicast().onBackpressureBuffer();
    private String id;
    private Flux<Object> messageSetsFlux;

    /**
     * <h2>{@link #MessageSets(String, Flux)}</h2>
     *
     * @param id
     * @param messageSetsFlux
     */
    public MessageSets(String id, Flux<Object> messageSetsFlux) {
        this.id = id;
        this.messageSetsFlux = messageSetsFlux;
    }

    /**
     * <h2>{@link #saveMessageSet(Flux)}</h2>
     *
     * @param messageSet
     * @return Flux<Object>
     */
    public static Flux<Object> saveMessageSet(Flux<Object> messageSet) {
        return messageSet.doOnNext(messageSetsSink::tryEmitNext).thenMany(messageSetsSink.asFlux());
    }

    /**
     * <h2>{@link #queryMessageSets()}</h2>
     *
     * @return Flux<Object>
     */
    public static Flux<Object> queryMessageSets() {
        return messageSetsSink.asFlux();
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public Flux<Object> getMessageSetsFlux() { return messageSetsFlux; }

    public void setMessageSetsFlux(Flux<Object> messageSetsFlux) { this.messageSetsFlux = messageSetsFlux; }
}
