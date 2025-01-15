package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.contexts.recomondation.RecommendationSystems;

/**
 * <h1>{@link UserInputContextualAdviserIface}</h1>
 * Interface for User Input Contextual Advisers.
 * <ul>
 *     <li>{@link NaturalLanguageProcessing}</li>
 *     <li>{@link RecommendationSystems}</li>
 * </ul>
 */
public interface UserInputContextualAdviserIface<T> {
    T processUserInput(T userRequest);
    String getNaturalLanguageProcessingContext(NaturalLanguageProcessing nlp);
    String getRecommendationSystemsContext(RecommendationSystems recommendationSystems);
}
