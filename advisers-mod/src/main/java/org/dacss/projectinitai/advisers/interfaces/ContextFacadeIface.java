package org.dacss.projectinitai.advisers.interfaces;

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
@FunctionalInterface
public interface ContextFacadeIface<T> {

    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest
     * @param aiResponse
     * @return T - updated context
     */
    T updateContext(T userRequest, T aiResponse);
}
