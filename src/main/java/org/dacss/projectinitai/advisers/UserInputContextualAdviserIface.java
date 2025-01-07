package org.dacss.projectinitai.advisers;

/**
 * <h1>{@link UserInputContextualAdviserIface}</h1>
 * Interface for User Input Contextual Advisers.
 */
@FunctionalInterface
public interface UserInputContextualAdviserIface<T> {
    T processUserInput(T userRequest);
}
