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
     *
     * @return String - system information
     */
    @Override
    public String getSystemInfo() {
        return STR."""
        \{ContextType.NATURAL_LANGUAGE_PROCESSING.getContextMessage()}
        \{ContextType.SPEECH_RECOGNITION.getContextMessage()}
        \{ContextType.KNOWLEDGE_REPRESENTATION_REASONING.getContextMessage()}
        """;
    }

    /**
     * {@link #getToolInfo()}
     *
     * @return String - tool information
     */
    @Override
    public String getToolInfo() {
        return STR."""
        \{ContextType.GENERATIVE.getContextMessage()}
        \{ContextType.REINFORCEMENT_LEARNING.getContextMessage()}
        \{ContextType.COMPUTER_VISION.getContextMessage()}
        """;
    }

    /**
     * {@link #getUserInfo()}
     *
     * @return String - user information
     */
    @Override
    public String getUserInfo() {
        return STR."""
        \{ContextType.RECOMMENDATION_SYSTEMS.getContextMessage()}
        \{ContextType.PREDICTIVE_ANALYTICS.getContextMessage()}
        \{ContextType.ROBOTICS.getContextMessage()}
        """;
    }

    /**
     * {@link #getDataInfo()}
     *
     * @return String - data information
     */
    @Override
    public String getDataInfo() {
        return STR."""
        \{ContextType.OPTIMIZATION.getContextMessage()}
        \{ContextType.RECOGNITION.getContextMessage()}
        """;
    }

    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest T
     * @param aiResponse  T
     * @return T - updated context
     */
    @Override
    public T updateContext(T userRequest, T aiResponse) {
        return CAC.updateContext(userRequest, aiResponse);
    }

    /**
     * {@link #processUserInput(T)}
     *
     * @param userRequest T
     * @return T - processed user request
     */
    @Override
    public T processUserInput(T userRequest) {
        return CAC.processUserInput(userRequest);
    }

    /**
     * {@link #processAIOutput(T)}
     *
     * @param aiResponse T
     * @return T - processed AI response
     */
    @Override
    public T processAIOutput(T aiResponse) {
        return CAC.processAIOutput(aiResponse);
    }

    /**
     * {@link #getGenerativeContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - generative context
     */
    @Override
    public String getGenerativeContext(ContextType contextType) {
        return contextType.getContextMessage();
    }

    /**
     * {@link #getOptimizationContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - optimization context
     */
    @Override
    public String getOptimizationContext(ContextType contextType) {
        return contextType.getContextMessage();
    }

    /**
     * {@link #getComputerVisionContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - computer vision context
     */
    @Override
    public String getComputerVisionContext(ContextType contextType) {
        return contextType.getContextMessage();
    }

    /**
     * {@link #getRoboticsContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - robotics context
     */
    @Override
    public String getRoboticsContext(ContextType contextType) {
        return contextType.getContextMessage();
    }

    /**
     * {@link #getKnowledgeRepresentationReasoningContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - knowledge representation reasoning context
     */
    @Override
    public String getKnowledgeRepresentationReasoningContext(ContextType contextType) {
        return contextType.getContextMessage();
    }

    /**
     * {@link #getPredictiveAnalyticsContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - predictive analytics context
     */
    @Override
    public String getPredictiveAnalyticsContext(ContextType contextType) {
        return contextType.getContextMessage();
    }

    /**
     * {@link #handleData(T)}
     *
     * @param data T
     * @return T - handled data
     */
    @Override
    public T handleData(T data) {
        return CAC.updateContext(data, data);
    }

    /**
     * {@link #getNaturalLanguageProcessingContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - natural language processing context
     */
    @Override
    public String getNaturalLanguageProcessingContext(ContextType contextType) {
        return contextType.getContextMessage();
    }

    /**
     * {@link #getRecommendationSystemsContext(ContextType)}
     *
     * @param contextType {@link ContextType}
     * @return String - recommendation systems context
     */
    @Override
    public String getRecommendationSystemsContext(ContextType contextType) {
        return contextType.getContextMessage();
    }
}
