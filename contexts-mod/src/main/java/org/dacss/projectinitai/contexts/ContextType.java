package org.dacss.projectinitai.contexts;
/**/

import org.dacss.projectinitai.krr.KRRContexts;
import org.dacss.projectinitai.nlp.NLPContexts;
import org.dacss.projectinitai.robotics.RoboticsContexts;
import org.dacss.projectinitai.vision.VisionContexts;
import org.dacss.projectinitai.generative.GenerativeContexts;

/**
 * <h1>{@link ContextType}</h1>
 * Enum class representing the different types of Contexts.
 * Each enum constant has a context message that provides a brief description of the purpose of the Context.
 */
public enum ContextType {
    GENERATIVE(GenerativeContexts.class),
    KNOWLEDGE_REPRESENTATION_REASONING(KRRContexts.class),
    NATURAL_LANGUAGE_PROCESSING(NLPContexts.class),
    OPTIMIZATION(OptimizationContexts.class),
    PREDICTIVE_ANALYTICS(PredictiveContexts.class),
    RECOGNITION(RecognitionContexts.class),
    RECOMMENDATION_SYSTEMS(RecommendationContexts.class),
    REINFORCEMENT_LEARNING(ReinforcementContexts.class),
    ROBOTICS(RoboticsContexts.class),
    SPEECH_RECOGNITION(SpeechContexts.class),
    COMPUTER_VISION(VisionContexts.class);

    private final Class<?> contextClass;

    ContextType(Class<?> contextClass) {
        this.contextClass = contextClass;
    }

    /**
     * Get context message based on the provided context type.
     *
     * @return String
     */
    public String getContextMessage() {
        try {
            return (String) contextClass.getMethod("getContextMessage").invoke(contextClass.getEnumConstants()[0]);
        } catch (Exception e) {
            throw new RuntimeException("Error getting context message", e);
        }
    }
}
