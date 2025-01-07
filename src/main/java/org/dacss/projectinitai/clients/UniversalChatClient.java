package org.dacss.projectinitai.clients;

import org.springframework.stereotype.Service;

/**
 * <h1>{@link UniversalChatClient}</h1>
 * UniversalChatClient is a class backend that is used to chat with any local or remote LLM model.
 * Defaults to the {@link org.dacss.projectinitai.views.chat.ChatView} for the user interface,
 * but can be used via terminal.
 */
@Service
public class UniversalChatClient {

    /**
     * {@link #sendMessage(String)}
     * Sends a message to the LLM model and receives a response.
     * @param message The message to send.
     * @return A formatted string containing both the user's request and the response from the LLM model.
     */
    public String sendMessage(String message) {
        // Implement the logic to send the message to the LLM model and receive the response.
        // This is a placeholder implementation.
        String response = "Response from LLM model to message: " + message;
        return "USER: " + message + "\nAI: " + response;
    }
}
