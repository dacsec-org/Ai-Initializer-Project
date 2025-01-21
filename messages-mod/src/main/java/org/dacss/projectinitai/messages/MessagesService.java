package org.dacss.projectinitai.messages;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link MessagesService}</h1>
 * Backend hilla endpoint service for message operations.
 */
@Service
@BrowserCallable
public class MessagesService {

    private MessagesHandler handler;

    /**
     * <h2>{@link #MessagesService()}</h2>
     * 0-arg constructor to instantiate the {@link MessagesHandler}.
     */
    public MessagesService() {
        this.handler = new MessagesHandler();
    }

    /**
     * <h2>{@link #handleMessageAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param message The message to be processed.
     * @return The result of the action.
     */
    public Object handleMessageAction(String action, String message) {
        return switch (action.toUpperCase()) {
            case "REQUEST" -> handler.handleRequest(message);
            case "RESPONSE" -> handler.handleResponse(message);
            case "THUMBS_UP" -> handler.handleThumbsUp(message);
            case "THUMBS_DOWN" -> handler.handleThumbsDown(message);
            case "TRASH" -> handler.handleTrash(message);
            case "RETRY" -> handler.handleRetry(message);
            default -> "Unknown action type";
        };
    }
}
