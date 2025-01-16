package org.dacss.projectinitai.advisers.components;
/**/
import org.dacss.projectinitai.contexts.interfaces.ContextType;
/**/
import com.vaadin.flow.component.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link ContextualAdviserComp}</h1>
 * <p>
 * This class is a component that provides methods to update, process, and clear the context of the application.
 * </p>
 *
 * @param <T>
 */
@Component
public class ContextualAdviserComp<T> {

    private static final Logger log = LoggerFactory.getLogger(ContextualAdviserComp.class);
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
        } catch (Exception e) {
            log.error("Error updating context: ", e);
            Notification.show(STR."Error updating context: \{e.getMessage()}");
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
        } catch (Exception e) {
            log.error("Error processing user input: ", e);
            Notification.show(STR."Error processing user input: \{e.getMessage()}");
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
        } catch (Exception e) {
            log.error("Error processing AI output: ", e);
            Notification.show(STR."Error processing AI output: \{e.getMessage()}");
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
        Notification.show("Context cleared.");
    }

    /**
     * {@link #addCustomContextEntry(String)}
     *
     * @param entry
     */
    public void addCustomContextEntry(String entry) {
        context.append(entry).append("\n");
        log.info("Custom context entry added: {}", entry);
        Notification.show(STR."Custom context entry added: \{entry}");
    }
}
