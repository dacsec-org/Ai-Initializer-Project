package org.dacss.projectinitai.advisers;

/**
 * <h1>{@link ContextualAdviserIface}</h1>
 * Interface for Contextual Advisers.
 *
 */
@FunctionalInterface
public interface ContextualAdviserIface<T> {
    T updateContext(T userRequest, T aiResponse);
}
