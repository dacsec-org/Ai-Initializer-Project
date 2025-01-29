package org.dacss.projectinitai.services;
/**/
import org.dacss.projectinitai.messages.MessageAction;
import org.dacss.projectinitai.messages.MessagesIface;
import org.dacss.projectinitai.messages.utillities.AiResponseUtil;
import org.dacss.projectinitai.messages.utillities.ThumbsDownUtil;
import org.dacss.projectinitai.messages.utillities.ThumbsUpUtil;
import org.dacss.projectinitai.messages.utillities.UserRequestUtil;
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
/**/
import static org.dacss.projectinitai.messages.utillities.AiResponseUtil.receiveAiResponseFromLLM;
import static org.dacss.projectinitai.messages.utillities.RetryMessageUtil.processRetryMessage;
import static org.dacss.projectinitai.messages.utillities.ThumbsDownUtil.processThumbsDown;
import static org.dacss.projectinitai.messages.utillities.ThumbsUpUtil.processThumbsUp;
import static org.dacss.projectinitai.messages.utillities.TrashMessageSetUtil.destroyMessageSet;
import static org.dacss.projectinitai.messages.utillities.UserRequestUtil.sendUserRequestToLLM;

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

    /**
     * <h3>{@link #MessagesService()}</h3>
     * 0-argument constructor.
     */
    public MessagesService() {}

    /**
     * <h3>{@link #processMessages(String)}</h3>
     *
     * @param action The action to perform.
     */
    @Override
    public void processMessages(String action) {
        try {
            Flux<String> flux;
            MessageAction messageAction = MessageAction.valueOf(action.toUpperCase());
            switch (messageAction) {
                case REQUEST:
                    flux = sendUserRequestToLLM(Flux.just(""));
                    break;
                case RESPONSE:
                    flux = receiveAiResponseFromLLM(Flux.just(""));
                    break;
                case THUMBS_UP:
                    flux = processThumbsUp(Flux.just(""));
                    break;
                case THUMBS_DOWN:
                    flux = processThumbsDown(Flux.just(""));
                    break;
                case TRASH:
                    flux = destroyMessageSet(Flux.just(""));
                    break;
                case RETRY:
                    flux = processRetryMessage(Flux.just(""));
                    break;
                default:
                    log.error("Invalid message type: {}", action);
                    return;
            }
            /*
             * Subscribe to the flux.
             */
            flux.subscribe(new CoreSubscriber<>() {
                /**
                 * <h2>{@link #onSubscribe(Subscription)}</h2>
                 *
                 * @param s The subscription.
                 */
                @Override
                public void onSubscribe(Subscription s) {
                    s.request(Long.MAX_VALUE);
                }

                /**
                 * <h2>{@link #onNext(String)}</h2>
                 *
                 * @param message The message.
                 */
                @Override
                public void onNext(String message) {
                    log.info("Message: {}", message);
                }

                /**
                 * <h2>{@link #onError(Throwable)}</h2>
                 *
                 * @param t The throwable.
                 */
                @Override
                public void onError(Throwable t) {
                    log.error("Error processing message: {}", action, t);
                }

                /**
                 * <h2>{@link #onComplete()}</h2>
                 */
                @Override
                public void onComplete() {
                    log.info("Message processing complete: {}", action);
                }
            });
        } catch (Exception messageExc) {
            log.error("Error processing message: {}", action, messageExc);
        }
    }
}
