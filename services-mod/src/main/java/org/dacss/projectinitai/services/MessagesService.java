package org.dacss.projectinitai.services;
/**/
import org.dacss.projectinitai.messages.MessageAction;
import org.dacss.projectinitai.messages.MessagesIface;
import org.dacss.projectinitai.messages.utillities.*;
/**/
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link MessagesService}</h1>
 * Backend hilla endpoint service for message operations.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class MessagesService implements MessagesIface {

    private static final Logger log = LoggerFactory.getLogger(MessagesService.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public MessagesService() {}

    @Override
    public Flux<Object> processMessages(MessageAction action) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case REQUEST -> UserRequestUtil.sendUserRequestToLLM(Flux.just(""));
                case RESPONSE -> AiResponseUtil.receiveAiResponseFromLLM(Flux.just(""));
                case THUMBS_UP -> ThumbsUpUtil.processThumbsUp(Flux.just(""));
                case THUMBS_DOWN -> ThumbsDownUtil.processThumbsDown(Flux.just(""));
                case RETRY -> RetryMessageUtil.retryMessageSet(Flux.just(""));
                case TRASH -> TrashMessageSetUtil.destroyMessageSet(Flux.just(""));
            };
        } catch (Exception messagesServiceExc) {
            log.error(RED + "Error from MessagesService performing action: {}" + RESET, action, messagesServiceExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "MessagesService action completed: {}" + RESET, action);
        }
        return flux;
    }
}
