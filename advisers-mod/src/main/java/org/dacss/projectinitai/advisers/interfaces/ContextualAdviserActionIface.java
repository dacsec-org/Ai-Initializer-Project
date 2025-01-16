package org.dacss.projectinitai.advisers.interfaces;

/**
 * <h1>{@link ContextualAdviserActionIface}</h1>
 * Functional interface for contextual adviser actions.
 */
@FunctionalInterface
public interface ContextualAdviserActionIface {

    /**
     * <h2>{@link #performAction(String)}</h2>
     * Perform an action based on the provided input.
     *
     * @param input The input data for performing the action.
     * @return String - the result of the action.
     */
    String performAction(String input);
}
