package org.dacss.projectinitai.types;

import org.dacss.projectinitai.generative.Generative;
import org.dacss.projectinitai.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.optimization.Optimization;
import org.dacss.projectinitai.predictive.PredictiveAnalytics;
import org.dacss.projectinitai.recomondation.RecommendationSystems;
import org.dacss.projectinitai.reinforcement.ReinforcementLearning;
import org.dacss.projectinitai.robotics.Robotics;
import org.dacss.projectinitai.speech.SpeechRecognition;
import org.dacss.projectinitai.vision.ComputerVision;

/**
 * <h1>{@link GenericTypes}</h1>
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
