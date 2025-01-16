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

    /**
     * {@link #processUserInput(T)}
     *
     * @param userRequest
     * @return T - processed user request
     */
    T processUserInput(T userRequest);

    /**
     * {@link #getNaturalLanguageProcessingContext(ContextType)}
     *
     * @param contextType
     * @return String - natural language processing context
     */
    String getNaturalLanguageProcessingContext(ContextType contextType);

    /**
     * {@link #getRecommendationSystemsContext(ContextType)}
     *
     * @param contextType
     * @return String - recommendation systems context
     */
    String getRecommendationSystemsContext(ContextType contextType);
}
