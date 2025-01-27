package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.messages.MessagesIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.dacss.projectinitai.messages.utillities.AiResponseUtil.getAiResponse;
import static org.dacss.projectinitai.messages.utillities.ThumbsDownUtil.processThumbsDown;
import static org.dacss.projectinitai.messages.utillities.ThumbsUpUtil.processThumbsUp;
import static org.dacss.projectinitai.messages.utillities.TrashMessageSetUtil.destroyMessageSet;
import static org.dacss.projectinitai.messages.utillities.UserRequestUtil.processUserRequest;

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
     * <h2>{@link #MessagesService()}</h2>
     * 0-argument constructor.
     */
    public MessagesService() {
    }

    /**
     * <h2>{@link #processMessages(String, String)}</h2>
     *
     * @param action The action to perform.
     * @param message The message to process.
     */
    @Override
    public void processMessages(String action, String message) {
        try {
            switch (action) {
                case "request":
                    processUserRequest(message);
                    break;
                case "response":
                    getAiResponse(message);
                    break;
                case "thumbs_up":
                    processThumbsUp(message);
                    break;
                case  "thumbs_down":
                    processThumbsDown(message);
                    break;
                case "trash":
                    destroyMessageSet(message);
                    break;
                case "retry":
                    processRetryMessage(message);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid action: " + action);
            }
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage());
        }
    }

    private void processRetryMessage(String message) {}
}
