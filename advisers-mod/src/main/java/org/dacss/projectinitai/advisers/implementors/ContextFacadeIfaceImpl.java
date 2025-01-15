package org.dacss.projectinitai.advisers.implementors;

import org.dacss.projectinitai.advisers.components.ContextualAdviserComp;
import org.dacss.projectinitai.advisers.interfaces.ContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.DataHandlerContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.UserInputContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.AIOutputContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.ContextFacadeIface;
import org.dacss.projectinitai.contexts.interfaces.ContextType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <h1>{@link ContextFacadeIfaceImpl}</h1>
 * Implementation of {@link ContextFacadeIface}.
 * <ul>
 *     <li>{@link AIOutputContextualAdviserIface}</li>
 *     <li>{@link ContextualAdviserIface}</li>
 *     <li>{@link DataHandlerContextualAdviserIface}</li>
 *     <li>{@link UserInputContextualAdviserIface}</li>
 * </ul>
 */
@Component
public class ContextFacadeIfaceImpl<T> implements
        ContextFacadeIface<T>
        , AIOutputContextualAdviserIface<T>
        , ContextualAdviserIface<T>
        , DataHandlerContextualAdviserIface<T>
        , UserInputContextualAdviserIface<T> {

    /**
     * {@link ContextualAdviserComp}
     */
    private final ContextualAdviserComp<T> CAC;

    /**
     * {@link #ContextFacadeIfaceImpl(ContextualAdviserComp)}
     *
     * @param CAC {@link ContextualAdviserComp<T>}
     */
    @Autowired
    public ContextFacadeIfaceImpl(ContextualAdviserComp<T> CAC) {
        this.CAC = CAC;
    }

    /**
     * {@link #getSystemInfo()}
     * <p>
     * Get System Information.
     *
     * @return String
     * </p>
     */
    @Override
    public String getSystemInfo() {
        return STR."""
        \{CAC.getContextMessage(ContextType.NATURAL_LANGUAGE_PROCESSING)}
        \{CAC.getContextMessage(ContextType.SPEECH_RECOGNITION)}
        \{CAC.getContextMessage(ContextType.KNOWLEDGE_REPRESENTATION_REASONING)}
        """;
    }

    /**
     * {@link #getToolInfo()}
     * <p>
     * Get Tool Information.
     *
     * @return String
     * </p>
     */
    @Override
    public String getToolInfo() {
        return STR."""
        \{CAC.getContextMessage(ContextType.GENERATIVE)}
        \{CAC.getContextMessage(ContextType.REINFORCEMENT_LEARNING)}
        \{CAC.getContextMessage(ContextType.COMPUTER_VISION)}
        """;
    }

    /**
     * {@link #getUserInfo()}
     * <p>
     * Get User Information.
     *
     * @return String
     * </p>
     */
    @Override
    public String getUserInfo() {
        return STR."""
        \{CAC.getContextMessage(ContextType.RECOMMENDATION_SYSTEMS)}
        \{CAC.getContextMessage(ContextType.PREDICTIVE_ANALYTICS)}
        \{CAC.getContextMessage(ContextType.ROBOTICS)}
        """;
    }

    /**
     * {@link #getDataInfo()}
     * <p>
     * Get Data Information.
     *
     * @return String
     * </p>
     */
    @Override
    public String getDataInfo() {
        return STR."""
        \{CAC.getContextMessage(ContextType.OPTIMIZATION)}
        \{CAC.getContextMessage(ContextType.RECOGNITION)}
        """;
    }

    /**
     * {@link #updateContext(T, T)}
     * <p>
     * Perform Contextual Adviser update.
     *
     * @param userRequest T
     * @param aiResponse  T
     * @return T
     * </p>
     */
    @Override
    public T updateContext(T userRequest, T aiResponse) {
        return CAC.updateContext(userRequest, aiResponse);
    }

    /**
     * {@link #processUserInput(T)}
     * <p>
     * Process User Input.
     *
     * @param userRequest T
     * @return T
     * </p>
     */
    @Override
    public T processUserInput(T userRequest) {
        return CAC.processUserInput(userRequest);
    }

    /**
     * {@link #processAIOutput(T)}
     * <p>
     * Process AI Output.
     *
     * @param aiResponse T
     * @return T
     * </p>
     */
    @Override
    public T processAIOutput(T aiResponse) {
        return CAC.processAIOutput(aiResponse);
    }

    /**
     * {@link #getGenerativeContext(ContextType)}
     * <p>
     * Get Generative Context.
     *
     * @param contextType {@link ContextType}
     * @return String
     * </p>
     */
    @Override
    public String getGenerativeContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }

    /**
     * {@link #getOptimizationContext(ContextType)}
     * <p>
     * Get Optimization Context.
     *
     * @param contextType {@link ContextType}
     * @return String
     * </p>
     */
    @Override
    public String getOptimizationContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }

    /**
     * {@link #getComputerVisionContext(ContextType)}
     * <p>
     * Get Computer Vision Context.
     *
     * @param contextType {@link ContextType}
     * @return String
     * </p>
     */
    @Override
    public String getComputerVisionContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }

    /**
     * {@link #getRoboticsContext(ContextType)}
     * <p>
     * Get Robotics Context.
     *
     * @param contextType {@link ContextType}
     * @return String
     * </p>
     */
    @Override
    public String getRoboticsContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }

    /**
     * {@link #getKnowledgeRepresentationReasoningContext(ContextType)}
     * <p>
     * Get Knowledge Representation Reasoning Context.
     *
     * @param contextType {@link ContextType}
     * @return String
     * </p>
     */
    @Override
    public String getKnowledgeRepresentationReasoningContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }

    /**
     * {@link #getPredictiveAnalyticsContext(ContextType)}
     * <p>
     * Get Predictive Analytics Context.
     *
     * @param contextType {@link ContextType}
     * @return String
     * </p>
     */
    @Override
    public String getPredictiveAnalyticsContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }

    /**
     * {@link #handleData(T)}
     * <p>
     * Handle Data.
     *
     * @param data T
     * @return T
     * </p>
     */
    @Override
    public T handleData(T data) {
        return CAC.updateContext(data, data);
    }

    /**
     * {@link #getNaturalLanguageProcessingContext(ContextType)}
     * <p>
     * Get Natural Language Processing Context.
     *
     * @param contextType {@link ContextType}
     * @return String
     * </p>
     */
    @Override
    public String getNaturalLanguageProcessingContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }

    /**
     * {@link #getRecommendationSystemsContext(ContextType)}
     * <p>
     * Get Recommendation Systems Context.
     * </p>
     */
    @Override
    public String getRecommendationSystemsContext(ContextType contextType) {
        return CAC.getContextMessage(contextType);
    }
}
