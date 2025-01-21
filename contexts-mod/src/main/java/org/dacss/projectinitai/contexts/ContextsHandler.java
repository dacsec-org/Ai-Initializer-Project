package org.dacss.projectinitai.contexts;
/**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link ContextsHandler}</h1>
 * <p>
 * This class is a component that provides methods to update, process, and clear the context of the application.
 * </p>
 *
 * @param <T>
 */
@Component
public class ContextsHandler<T> {

    private static final Logger log = LoggerFactory.getLogger(ContextsHandler.class);
    private final StringBuilder context = new StringBuilder();
    private T lastUserRequest;
    private T lastAIResponse;

    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest
     * @param aiResponse
     * @return T
     */
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

    /**
     * {@link #processUserInput(T)}
     *
     * @param userRequest
     * @return T
     */
    public T processUserInput(T userRequest) {
        try {
            log.info("Processing user input: {}", userRequest);
            return userRequest;
        } catch (Exception processUserInputExc) {
            log.error("Error processing user input: ", processUserInputExc);
            return null;
        }
    }

    /**
     * {@link #processAIOutput(T)}
     *
     * @param aiResponse
     * @return T
     */
    public T processAIOutput(T aiResponse) {
        try {
            log.info("Processing AI output: {}", aiResponse);
            return aiResponse;
        } catch (Exception processAIOutputExc) {
            log.error("Error processing AI output: ", processAIOutputExc);
            return null;
        }
    }

    /**
     * {@link #getContext()}
     *
     * @return String
     */
    public String getContext() {
        return context.toString();
    }

    /**
     * {@link #clearContext()}
     */
    public void clearContext() {
        context.setLength(0);
        log.info("Context cleared.");
    }

    /**
     * {@link #addCustomContextEntry(String)}
     *
     * @param entry
     */
    public void addCustomContextEntry(String entry) {
        context.append(entry).append("\n");
        log.info("Custom context entry added: {}", entry);
    }
}
