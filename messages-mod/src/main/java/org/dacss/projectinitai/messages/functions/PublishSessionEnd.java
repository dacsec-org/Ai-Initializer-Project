package org.dacss.projectinitai.messages.functions;

import org.dacss.projectinitai.messages.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import java.util.Optional;

import static org.dacss.projectinitai.messages.MessageAction.SESSION_END;

/**
 * <h1>{@link PublishSessionEnd}</h1>
 * class to publish the end of a session to the LLM, message-database, messaging-mod using 'onComplete()'.
 */
public class PublishSessionEnd {

    private static Optional<Event> lastReceivedEvent = Optional.empty();
    /*warning->'Optional<Event>' used as type for field 'lastReceivedEvent'*/
    private static Sinks.Many<Event> eventPublisher = Sinks.many().unicast().onBackpressureBuffer();

    /**
     * <h3>{@link #publishSessionEnd()}</h3>
     * publishes the end of a session.
     */
    public static Flux<Object> publishSessionEnd() {
        return Flux.create(sink -> lastReceivedEvent.ifPresent(event -> {
            eventPublisher.tryEmitNext(
                Event.action(SESSION_END)
                    .withPayload(null)
                    .user(event.getUser())
                    .build()
            );
            eventPublisher.tryEmitComplete();
            sink.complete();
        }));
    }

    /**
     * <h3>{@link #setLastReceivedEvent(Event)}</h3>
     * @param event the event to be published.
     */
    public static void setLastReceivedEvent(Event event) {
        lastReceivedEvent = Optional.ofNullable(event);
    }
}
