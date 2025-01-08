package org.dacss.projectinitai.advisers.contexts;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <hi>{@link ContextualAdviserComponent}</hi>
 * Provides contextual advice to the AI system.
 * @param <T>
 */
@Slf4j
@Component
public class ContextualAdviserComponent<T> implements ContextualAdviserIface<T>
        , UserInputContextualAdviserIface<T>, AIOutputContextualAdviserIface<T> {

    private final StringBuilder context = new StringBuilder();
    @Getter
    private T lastUserRequest;
    @Getter
    private T lastAIResponse;

    @Override
    public T updateContext(T userRequest, T aiResponse) {
        try {
            lastUserRequest = userRequest;
            lastAIResponse = aiResponse;
            context.append("USER: ").append(userRequest.toString()).append("\n");
            context.append("AI: ").append(aiResponse.toString()).append("\n");
            log.info("Context updated with user request and AI response.");
            return userRequest;
        } catch (Exception e) {
            log.error("Error updating context: ", e);
            return null;
        }
    }

    @Override
    public T processUserInput(T userRequest) {
        try {
            log.info("Processing user input: {}", userRequest);
            return userRequest;
        } catch (Exception e) {
            log.error("Error processing user input: ", e);
            return null;
        }
    }

    @Override
    public T processAIOutput(T aiResponse) {
        try {
            log.info("Processing AI output: {}", aiResponse);
            return aiResponse;
        } catch (Exception e) {
            log.error("Error processing AI output: ", e);
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
