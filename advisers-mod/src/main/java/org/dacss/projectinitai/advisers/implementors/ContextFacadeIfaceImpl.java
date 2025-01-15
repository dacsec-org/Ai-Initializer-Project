package org.dacss.projectinitai.advisers.implementors;

import org.dacss.projectinitai.loaders.components.ContextualAdviserComp;
import org.dacss.projectinitai.advisers.interfaces.ContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.DataHandlerContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.UserInputContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.AIOutputContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.ContextFacadeIface;
import org.dacss.projectinitai.contexts.generative.Generative;
import org.dacss.projectinitai.contexts.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.contexts.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.contexts.optimization.Optimization;
import org.dacss.projectinitai.contexts.predictive.PredictiveAnalytics;
import org.dacss.projectinitai.contexts.recognition.Recognition;
import org.dacss.projectinitai.contexts.recomondation.RecommendationSystems;
import org.dacss.projectinitai.contexts.reinforcement.ReinforcementLearning;
import org.dacss.projectinitai.contexts.robotics.Robotics;
import org.dacss.projectinitai.contexts.speech.SpeechRecognition;
import org.dacss.projectinitai.contexts.vision.ComputerVision;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@Slf4j
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
     * @param CAC {@link ContextualAdviserComp<T>}
     */
    public ContextFacadeIfaceImpl(ContextualAdviserComp<T> CAC) {
        this.CAC = CAC;
    }

    /**
     * {@link #getSystemInfo()}
     * <p>
     * Get System Information.
     * @return String
     * </p>
     */
    @Override
    public String getSystemInfo() {
        return CAC.getContextMessage(NaturalLanguageProcessing.TEXT_GENERATION) + "\n" +
               CAC.getContextMessage(SpeechRecognition.SPEECH_TO_TEXT) +
                "\n" +
               CAC.getContextMessage(KnowledgeRepresentationReasoning.KNOWLEDGE_GRAPHS);
    }

    /**
     * {@link #getToolInfo()}
     * <p>
     * Get Tool Information.
     * @return String
     * </p>
     */
    @Override
    public String getToolInfo() {
        return CAC.getContextMessage(Generative.DEEPFAKES) + "\n" +
               CAC.getContextMessage(ReinforcementLearning.AUTONOMOUS_DRIVING) + "\n" +
               CAC.getContextMessage(ComputerVision.IMAGE_CLASSIFICATION);
    }

    /**
     * {@link #getUserInfo()}
     * <p>
     * Get User Information.
     * @return String
     * </p>
     */
    @Override
    public String getUserInfo() {
        return CAC.getContextMessage(RecommendationSystems.COLLABORATIVE_FILTERING) + "\n" +
               CAC.getContextMessage(PredictiveAnalytics.TIME_SERIES_FORECASTING) + "\n" +
               CAC.getContextMessage(Robotics.MOTION_CONTROL);
    }

    /**
     * {@link #getDataInfo()}
     * <p>
     *     Get Data Information.
     * @return String
     * </p>
     */
    @Override
    public String getDataInfo() {
        return CAC.getContextMessage(Optimization.LINEAR_PROGRAMMING) + "\n" +
               CAC.getContextMessage(Recognition.FACIAL_RECOGNITION);
    }

    /**
     * {@link #updateContext(T, T)}
     * <p>
     * Perform Contextual Adviser update.
     * @param userRequest T
     * @param aiResponse T
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
     * @param aiResponse T
     * @return T
     * </p>
     */
    @Override
    public T processAIOutput(T aiResponse) {
        return CAC.processAIOutput(aiResponse);
    }

    /**
     * {@link #getGenerativeContext(Generative)}
     * <p>
     * Get Generative Context.
     * @param generative {@link Generative}
     * @return String
     * </p>
     */
    @Override
    public String getGenerativeContext(Generative generative) {
        return CAC.getContextMessage(generative);
    }

    /**
     * {@link #getOptimizationContext(Optimization)}
     * <p>
     * Get Optimization Context.
     * @param optimization {@link Optimization}
     * @return String
     * </p>
     */
    @Override
    public String getOptimizationContext(Optimization optimization) {
        return CAC.getContextMessage(optimization);
    }

    /**
     * {@link #getComputerVisionContext(ComputerVision)}
     * <p>
     * Get Computer Vision Context.
     * @param computerVision {@link ComputerVision}
     * @return String
     * </p>
     */
    @Override
    public String getComputerVisionContext(ComputerVision computerVision) {
        return CAC.getContextMessage(computerVision);
    }

    /**
     * {@link #getRoboticsContext(Robotics)}
     * <p>
     * Get Robotics Context.
     * @param robotics {@link Robotics}
     * @return String
     * </p>
     */
    @Override
    public String getRoboticsContext(Robotics robotics) {
        return CAC.getContextMessage(robotics);
    }

    /**
     * {@link #getKnowledgeRepresentationReasoningContext(KnowledgeRepresentationReasoning)}
     * <p>
     * Get Knowledge Representation Reasoning Context.
     * @param krr {@link KnowledgeRepresentationReasoning}
     * @return String
     * </p>
     */
    @Override
    public String getKnowledgeRepresentationReasoningContext(KnowledgeRepresentationReasoning krr) {
        return CAC.getContextMessage(krr);
    }

    /**
     * {@link #getPredictiveAnalyticsContext(PredictiveAnalytics)}
     * <p>
     * Get Predictive Analytics Context.
     * @param predictiveAnalytics {@link PredictiveAnalytics}
     * @return String
     * </p>
     */
    @Override
    public String getPredictiveAnalyticsContext(PredictiveAnalytics predictiveAnalytics) {
        return CAC.getContextMessage(predictiveAnalytics);
    }

    /**
     * {@link #handleData(T)}
     * <p>
     * Handle Data.
     * @param data T
     * @return T
     * </p>
     */
    @Override
    public T handleData(T data) {
        return CAC.updateContext(data, data);
    }

    /**
     * {@link #getNaturalLanguageProcessingContext(NaturalLanguageProcessing)}
     * <p>
     * Get Natural Language Processing Context.
     * @param nlp {@link NaturalLanguageProcessing}
     * @return String
     * </p>
     */
    @Override
    public String getNaturalLanguageProcessingContext(NaturalLanguageProcessing nlp) {
        return CAC.getContextMessage(nlp);
    }

    /**
     * {@link #getRecommendationSystemsContext(RecommendationSystems)}
     * <p>
     * Get Recommendation Systems Context.
     * </p>
     */
    @Override
    public String getRecommendationSystemsContext(RecommendationSystems recommendationSystems) {
        return CAC.getContextMessage(recommendationSystems);
    }
}
