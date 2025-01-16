package org.dacss.projectinitai.types;

import org.dacss.projectinitai.contexts.generative.Generative;
import org.dacss.projectinitai.contexts.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.contexts.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.contexts.optimization.Optimization;
import org.dacss.projectinitai.contexts.predictive.PredictiveAnalytics;
import org.dacss.projectinitai.contexts.recomondation.RecommendationSystems;
import org.dacss.projectinitai.contexts.reinforcement.ReinforcementLearning;
import org.dacss.projectinitai.contexts.robotics.Robotics;
import org.dacss.projectinitai.contexts.speech.SpeechRecognition;
import org.dacss.projectinitai.contexts.vision.ComputerVision;

/**
 * <h1>{@link GenericTypes}</h1>
 * created to not have cyclic dependencies between the types and contexts modules.
 */
public enum GenericTypes {

    COMPUTER_VISION,
    GENERATIVE,
    KNOWLEDGE_REPRESENTATION_REASONING,
    NATURAL_LANGUAGE_PROCESSING,
    OPTIMIZATION,
    PREDICTIVE_ANALYTICS,
    RECOMMENDATION_SYSTEMS,
    REINFORCEMENT_LEARNING,
    ROBOTICS,
    SPEECH_RECOGNITION;

    public Enum<?>[] getValues() {
        return switch (this) {
            case NATURAL_LANGUAGE_PROCESSING -> NaturalLanguageProcessing.values();
            case GENERATIVE -> Generative.values();
            case RECOMMENDATION_SYSTEMS -> RecommendationSystems.values();
            case REINFORCEMENT_LEARNING -> ReinforcementLearning.values();
            case SPEECH_RECOGNITION -> SpeechRecognition.values();
            case COMPUTER_VISION -> ComputerVision.values();
            case KNOWLEDGE_REPRESENTATION_REASONING -> KnowledgeRepresentationReasoning.values();
            case OPTIMIZATION -> Optimization.values();
            case PREDICTIVE_ANALYTICS -> PredictiveAnalytics.values();
            case ROBOTICS -> Robotics.values();
        };
    }
}
