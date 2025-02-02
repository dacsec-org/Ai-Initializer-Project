package org.dacss.projectinitai.messages;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link MessagesIface}</h1>
 * <p>
 *     Functional interface for handling messages.
 * </p>
 */
@Endpoint
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface MessagesIface {

    Logger log = LoggerFactory.getLogger(MessagesIface.class);
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    static Flux<Object> processMessagesAction(MessageAction action) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case REQUEST -> UserRequest.sendUserRequestToLLM(Flux.just(new Object()));
                case RESPONSE -> AiResponse.receiveAiResponseFromLLM(Flux.just(new Object()));
                case THUMBS_UP -> ThumbsUpUtil.processThumbsUp(Flux.just(new Object()));
                case THUMBS_DOWN -> ThumbsDownUtil.processThumbsDown(Flux.just(new Object()));
                case RETRY -> RetryMessageUtil.retryMessageSet(Flux.just(new Object().toString()));
                case TRASH -> TrashMessageSetUtil.destroyMessageSet(Flux.just(new Object()));
            };
        } catch (Exception messagesServiceExc) {
            log.error(RED + "Error from MessagesIface performing action: {}" + RESET, action, messagesServiceExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "MessagesIface action completed: {}" + RESET, action);
        }
        return flux;
    }

    Flux<Object> processMessages(MessageAction action);
}
