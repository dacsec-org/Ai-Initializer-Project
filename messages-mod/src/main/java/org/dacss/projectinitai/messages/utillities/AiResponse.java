package org.dacss.projectinitai.messages.utillities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link AiResponse}</h1>
 * AiResponse is a class that represents the AI response object.
 */
@Component
public class AiResponse {

    private static Sinks.Many<Object> aiResponseSink = Sinks.many().unicast().onBackpressureBuffer();
    private String from;
    private Flux<Object> aiResponseFlux;

    /**
     * <h2>{@link #AiResponse(String, Flux)}</h2>
     *
     * @param from
     * @param aiResponseFlux
     */
    @Autowired
    AiResponse(String from, Flux<Object> aiResponseFlux) {
        this.from = from;
        this.aiResponseFlux = aiResponseFlux;
    }

    /**
     * <h2>{@link #receiveAiResponseFromLLM(Flux)}</h2>
     *
     * @param message
     * @return Flux<Object>
     */
    public static Flux<Object> receiveAiResponseFromLLM(Flux<Object> message) {
        return message.doOnNext(aiResponseSink::tryEmitNext).thenMany(aiResponseSink.asFlux());
    }

    public String getFrom() {return "AI";}

    public void setFrom(String from) {this.from = from;}

    public Flux<Object> getAiResponseFlux() {return aiResponseFlux;}

    public void setAiResponseFlux(Flux<Object> aiResponseFlux) {this.aiResponseFlux = aiResponseFlux;}
}
