package org.dacss.projectinitai.admin.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link ContextsHandler}</h1>
 * This class is a component that provides methods to update, process, and clear the context of the application.
 *
 * @param <T>
 */
public class ContextsHandler<T> {

    private static final Logger log = LoggerFactory.getLogger(ContextsHandler.class);
    private final StringBuilder context = new StringBuilder();
    private T lastUserRequest;
    private T lastAIResponse;

    public T updateContext(T userRequest, T aiResponse) {
        try {
            lastUserRequest = userRequest;
            lastAIResponse = aiResponse;
            context.append("USER: ").append(userRequest.toString()).append("\n");
            context.append("AI: ").append(aiResponse.toString()).append("\n");
            log.info("Context updated with user request and AI response.");
            return userRequest;
        } catch (Exception updateContextExc) {
            log.error("Error updating context: ", updateContextExc);
            return null;
        }
    }

    public T processUserInput(T userRequest) {
        try {
            log.info("Processing user input: {}", userRequest);
            return userRequest;
        } catch (Exception processUserInputExc) {
            log.error("Error processing user input: ", processUserInputExc);
            return null;
        }
    }

    public T processAIOutput(T aiResponse) {
        try {
            log.info("Processing AI output: {}", aiResponse);
            return aiResponse;
        } catch (Exception processAIOutputExc) {
            log.error("Error processing AI output: ", processAIOutputExc);
            return null;
        }
    }

    public String getContext() {
        return context.toString();
    }

    public void clearContext() {
        context.setLength(0);
        log.info("Context cleared.");
    }

    public void addCustomContextEntry(String entry) {
        context.append(entry).append("\n");
        log.info("Custom context entry added: {}", entry);
    }
}
