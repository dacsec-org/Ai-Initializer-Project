//package org.dacss.projectinitai.contexts;
//
//import org.dacss.projectinitai.contexts.generative.Generative;
//import org.dacss.projectinitai.contexts.krr.KnowledgeRepresentationReasoning;
//import org.dacss.projectinitai.contexts.nlp.NaturalLanguageProcessing;
//import org.dacss.projectinitai.contexts.optimization.Optimization;
//import org.dacss.projectinitai.contexts.predictive.PredictiveAnalytics;
//import org.dacss.projectinitai.contexts.recognition.Recognition;
//import org.dacss.projectinitai.contexts.recomondation.RecommendationSystems;
//import org.dacss.projectinitai.contexts.reinforcement.ReinforcementLearning;
//import org.dacss.projectinitai.contexts.robotics.Robotics;
//import org.dacss.projectinitai.contexts.speech.SpeechRecognition;
//import org.dacss.projectinitai.contexts.vision.ComputerVision;
//
///**
// * <h1>{@link ContextType}</h1>
// * Enum class representing the different types of Contexts.
// * Each enum constant has a context message that provides a brief description of the purpose of the Context.
// */
//public enum ContextType {
//    GENERATIVE(Generative.class),
//    KNOWLEDGE_REPRESENTATION_REASONING(KnowledgeRepresentationReasoning.class),
//    NATURAL_LANGUAGE_PROCESSING(NaturalLanguageProcessing.class),
//    OPTIMIZATION(Optimization.class),
//    PREDICTIVE_ANALYTICS(PredictiveAnalytics.class),
//    RECOGNITION(Recognition.class),
//    RECOMMENDATION_SYSTEMS(RecommendationSystems.class),
//    REINFORCEMENT_LEARNING(ReinforcementLearning.class),
//    ROBOTICS(Robotics.class),
//    SPEECH_RECOGNITION(SpeechRecognition.class),
//    COMPUTER_VISION(ComputerVision.class);
//
//    private final Class<?> contextClass;
//
//    ContextType(Class<?> contextClass) {
//        this.contextClass = contextClass;
//    }
//
//    /**
//     * Get context message based on the provided context type.
//     *
//     * @return String
//     */
//    public String getContextMessage() {
//        try {
//            return (String) contextClass.getMethod("getContextMessage").invoke(contextClass.getEnumConstants()[0]);
//        } catch (Exception e) {
//            throw new RuntimeException("Error getting context message", e);
//        }
//    }
//}
