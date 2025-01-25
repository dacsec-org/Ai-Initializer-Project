package org.dacss.projectinitai.advisers.interfaces;


/**
 * <h1>{@link UserInputContextualAdviserIface}</h1>
 * Interface for User Input Contextual Advisers.
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
