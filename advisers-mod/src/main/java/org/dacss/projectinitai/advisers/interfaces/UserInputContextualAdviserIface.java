package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.ContextType;

/**
 * <h1>{@link UserInputContextualAdviserIface}</h1>
 * Interface for User Input Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
@FunctionalInterface
public interface UserInputContextualAdviserIface<T> {

    /**
     * {@link #processUserInput(T)}
     *
     * @param userRequest
     * @return T - processed user request
     */
    T processUserInput(T userRequest);
}
