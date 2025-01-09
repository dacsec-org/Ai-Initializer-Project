package org.dacss.projectinitai.types;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.advisers.contexts.ContextualAdviserComponent;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link ContextFacadeIfaceImpl}</h1>
 * @param <T> the type of the context
 * @see ContextFacadeIface
 */
@Slf4j
@Component
public class ContextFacadeIfaceImpl<T> implements ContextFacadeIface<T> {

    private final ContextualAdviserComponent<T> contextualAdviserComponent;

    public ContextFacadeIfaceImpl(ContextualAdviserComponent<T> contextualAdviserComponent) {
        this.contextualAdviserComponent = contextualAdviserComponent;
    }

    @Override
    public String getSystemInfo() {
        return createMessage(
                "system"
                , "manages and monitors the overall health and performance of the local and remote systems");
    }

    @Override
    public String getToolInfo() {
        return createMessage(
                "tool"
                , "provides various utilities and functionalities to assist users in their tasks");
    }

    @Override
    public String getUserInfo() {
        return createMessage(
                "user interface"
                , "interacts with the end-users and gathers their input");
    }

    @Override
    public String getDataInfo() {
        return createMessage(
                "data handler"
                , "processes and manages the data within the application");
    }

    @Override
    public T updateContext(T userRequest, T aiResponse) {
        return contextualAdviserComponent.updateContext(userRequest, aiResponse);
    }

    @Override
    public T processUserInput(T userRequest) {
        return contextualAdviserComponent.processUserInput(userRequest);
    }

    @Override
    public T processAIOutput(T aiResponse) {
        return contextualAdviserComponent.processAIOutput(aiResponse);
    }

    /**
     * {@link #createMessage(String, String)}
     * @param type message type
     * @param task message task
     * @return the message
     */
    private String createMessage(String type, String task) {
        try {
            log.info("Creating message for {}: {}", type, task);
            return String.format("The %s %s.", type, task);
        } catch (Exception e) {
            log.error("Error creating message: ", e);
            return null;
        }
    }
}
