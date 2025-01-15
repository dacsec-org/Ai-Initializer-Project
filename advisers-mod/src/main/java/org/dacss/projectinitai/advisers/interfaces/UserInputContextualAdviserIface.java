package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.interfaces.ContextType;

/**
 * <h1>{@link UserInputContextualAdviserIface}</h1>
 * Interface for User Input Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
public interface UserInputContextualAdviserIface<T> {
    T processUserInput(T userRequest);
    String getNaturalLanguageProcessingContext(ContextType contextType);
    String getRecommendationSystemsContext(ContextType contextType);
}
