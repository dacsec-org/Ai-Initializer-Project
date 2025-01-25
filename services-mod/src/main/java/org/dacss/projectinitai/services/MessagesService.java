package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.messages.MessagesIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link MessagesService}</h1>
 * Backend hilla endpoint service for message operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class MessagesService implements MessagesIface {


    private static final Logger log = LoggerFactory.getLogger(MessagesService.class);

    /**
     * <h2>{@link #MessagesService()}</h2>
     */
    public MessagesService() {
    }

    /**
     * <h2>{@link #processMessages()}</h2>
     */
    @Override
    public void processMessages() {

    }
}

//    /**
//     * <h2>{@link #handleMessageAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param message The message to be processed.
//     * @return The result of the action.
//     */
//    public Object handleMessageAction(String action, String message) {
//        return switch (action.toUpperCase()) {
//            case "REQUEST" -> handler.handleRequest(message);
//            case "RESPONSE" -> handler.handleResponse(message);
//            case "THUMBS_UP" -> handler.handleThumbsUp(message);
//            case "THUMBS_DOWN" -> handler.handleThumbsDown(message);
//            case "TRASH" -> handler.handleTrash(message);
//            case "RETRY" -> handler.handleRetry(message);
//            default -> "Unknown action type";
//        };
//    }
//}
