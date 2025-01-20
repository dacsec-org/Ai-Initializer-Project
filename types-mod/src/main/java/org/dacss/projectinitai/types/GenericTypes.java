package org.dacss.projectinitai.types;

import org.dacss.projectinitai.contexts.enums.generative.Generative;
import org.dacss.projectinitai.contexts.enums.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.contexts.enums.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.contexts.enums.optimization.Optimization;
import org.dacss.projectinitai.contexts.enums.predictive.PredictiveAnalytics;
import org.dacss.projectinitai.contexts.enums.recomondation.RecommendationSystems;
import org.dacss.projectinitai.contexts.enums.reinforcement.ReinforcementLearning;
import org.dacss.projectinitai.contexts.enums.robotics.Robotics;
import org.dacss.projectinitai.contexts.enums.speech.SpeechRecognition;
import org.dacss.projectinitai.contexts.enums.vision.ComputerVision;

import javax.sound.midi.Sequence;

/**
 * <h1>{@link GenericTypes}</h1>
 * generic types of models.
 * created to not have cyclic dependencies between the types and contexts modules.
 */
public enum GenericTypes {

    ANOMALY_DETECTION, /*identify unusual patterns that do not conform to expected behavior.*/

    CLASSIFICATION,/*categorize data into predefined classes.*/

    CLUSTERING,/*group similar data points together.*/

    COMPUTER_VISION,/*Interpret and make decisions based on visual data.*/

    DIMENSIONALITY_REDUCTION,/*reduce the number of random variables under consideration.*/

    EMBEDDING,/*map high-dimensional data to a lower-dimensional space.*/

    GENERATIVE,/*generate new data instances similar to the training data*/

    KNOWLEDGE_REPRESENTATION_REASONING,/*represent knowledge in a form that a computer system can use to solve complex tasks.*/

    NATURAL_LANGUAGE_PROCESSING,/*process and analyze human language data.*/

    OPTIMIZATION,/*find the best solution from a set of possible solutions.*/

    PREDICTIVE_ANALYTICS,/*make predictions about future events based on historical data.*/

    RECOMMENDATION_SYSTEMS,/*suggest items to users based on their preferences.*/

    REGRESSION,/*predict continuous values*/

    REINFORCEMENT_LEARNING,/*make a sequence of decisions by learning from the environment.*/

    ROBOTICS,/*control and manage robotic systems*/

    SEQUENCE_MODELING,/*handle sequential data, such as time series or natural language.*/

    SPEECH_RECOGNITION;/*convert spoken language into text.*/

    public Enum<?>[] getValues() {
        return switch (this) {
            case ANOMALY_DETECTION -> AnomalyDetection.values();
            case CLASSIFICATION -> Classification.values();
            case CLUSTERING -> Clustering.values();
            case COMPUTER_VISION -> ComputerVision.values();
            case DIMENSIONALITY_REDUCTION -> DimensionalityReduction.values();
            case EMBEDDING -> Embedding.values();
            case GENERATIVE -> Generative.values();
            case KNOWLEDGE_REPRESENTATION_REASONING -> KnowledgeRepresentationReasoning.values();
            case NATURAL_LANGUAGE_PROCESSING -> NaturalLanguageProcessing.values();
            case OPTIMIZATION -> Optimization.values();
            case PREDICTIVE_ANALYTICS -> PredictiveAnalytics.values();
            case RECOMMENDATION_SYSTEMS -> RecommendationSystems.values();
            case REGRESSION -> Regression.values();
            case REINFORCEMENT_LEARNING -> ReinforcementLearning.values();
            case ROBOTICS -> Robotics.values();
            case SEQUENCE_MODELING -> SequenceModeling.values();
            case SPEECH_RECOGNITION -> SpeechRecognition.values();
        };
    }
}
