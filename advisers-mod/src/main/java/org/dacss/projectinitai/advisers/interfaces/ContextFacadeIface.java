package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.interfaces.ContextType;

/**
 * <h1>{@link ContextFacadeIface}</h1>
 * Facade Interface for Contextual Advisers.
 * <ul>
 *     <li>{@link AIOutputContextualAdviserIface}</li>
 *     <li>{@link ContextualAdviserIface}</li>
 *     <li>{@link DataHandlerContextualAdviserIface}</li>
 *     <li>{@link UserInputContextualAdviserIface}</li>
 * </ul>
 */
public interface ContextFacadeIface<T> extends
        AIOutputContextualAdviserIface<T>
        , ContextualAdviserIface<T>
        , DataHandlerContextualAdviserIface<T>
        , UserInputContextualAdviserIface<T> {
    String getSystemInfo();
    String getToolInfo();
    String getUserInfo();
    String getDataInfo();
    T updateContext(T userRequest, T aiResponse);
    T processUserInput(T userRequest);
    T processAIOutput(T aiResponse);
}
