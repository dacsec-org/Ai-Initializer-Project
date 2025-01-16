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

    /**
     * {@link #getSystemInfo()}
     *
     * @return String - system information
     */
    String getSystemInfo();

    /**
     * {@link #getToolInfo()}
     *
     * @return String - tool information
     */
    String getToolInfo();

    /**
     * {@link #getUserInfo()}
     *
     * @return String - user information
     */
    String getUserInfo();

    /**
     * {@link #getDataInfo()}
     *
     * @return String - data information
     */
    String getDataInfo();


    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest
     * @param aiResponse
     * @return T - updated context
     */
    T updateContext(T userRequest, T aiResponse);

    /**
     * {@link #processUserInput(T)}
     *
     * @param userRequest
     * @return T - processed user request
     */
    T processUserInput(T userRequest);

    /**
     * {@link #processAIOutput(T)}
     *
     * @param aiResponse
     * @return T - processed AI response
     */
    T processAIOutput(T aiResponse);
}
