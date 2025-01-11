package org.dacss.projectinitai.advisers.contexts;

import org.dacss.projectinitai.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.recomondation.RecommendationSystems;

public interface UserInputContextualAdviserIface<T> {
    T processUserInput(T userRequest);
    String getNaturalLanguageProcessingContext(NaturalLanguageProcessing nlp);
    String getRecommendationSystemsContext(RecommendationSystems recommendationSystems);
}
