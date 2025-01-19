package org.dacss.projectinitai.messages.services;
/**/
import org.dacss.projectinitai.messages.handlers.MessagesHandler;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link MessagesService}</h1>
 */
@Service
@BrowserCallable
public class MessagesService {

    @Autowired
    private MessagesHandler messagesHandler;

    public String handleMessageAction(String actionType, String message) {
        return switch (actionType) {
            case "REQUEST" -> messagesHandler.handleRequest(message);
            case "RESPONSE" -> messagesHandler.handleResponse(message);
            case "THUMBS_UP" -> messagesHandler.handleThumbsUp(message);
            case "THUMBS_DOWN" -> messagesHandler.handleThumbsDown(message);
            case "TRASH" -> messagesHandler.handleTrash(message);
            case "RETRY" -> messagesHandler.handleRetry(message);
            default -> "Unknown action type";
        };
    }
}
