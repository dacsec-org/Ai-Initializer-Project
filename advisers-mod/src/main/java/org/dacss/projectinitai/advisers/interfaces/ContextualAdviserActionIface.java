package org.dacss.projectinitai.advisers.interfaces;

/**
 * <h1>{@link ContextualAdviserActionIface}</h1>
 * Functional interface for contextual adviser actions.
 */
@FunctionalInterface
public interface ContextualAdviserActionIface {
    /**
     * Perform an action based on the provided input.
     *
     * @param input The input data for performing the action.
     * @return The result of the action performed based on the input data.
     */
    String performAction(String input);
}
