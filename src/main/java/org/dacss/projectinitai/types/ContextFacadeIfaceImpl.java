package org.dacss.projectinitai.types;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.components.ContextualAdviserComp;
import org.dacss.projectinitai.generative.Generative;
import org.dacss.projectinitai.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.optimization.Optimization;
import org.dacss.projectinitai.predictive.PredictiveAnalytics;
import org.dacss.projectinitai.recognition.Recognition;
import org.dacss.projectinitai.recomondation.RecommendationSystems;
import org.dacss.projectinitai.reinforcement.ReinforcementLearning;
import org.dacss.projectinitai.robotics.Robotics;
import org.dacss.projectinitai.speech.SpeechRecognition;
import org.dacss.projectinitai.vision.ComputerVision;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContextFacadeIfaceImpl<T> implements ContextFacadeIface<T> {

    private final ContextualAdviserComp<T> contextualAdviserComp;

    public ContextFacadeIfaceImpl(ContextualAdviserComp<T> contextualAdviserComp) {
        this.contextualAdviserComp = contextualAdviserComp;
    }

    @Override
    public String getSystemInfo() {
        return contextualAdviserComp.getContextMessage(NaturalLanguageProcessing.TEXT_GENERATION) + "\n" +
               contextualAdviserComp.getContextMessage(SpeechRecognition.SPEECH_TO_TEXT) + "\n" +
               contextualAdviserComp.getContextMessage(KnowledgeRepresentationReasoning.KNOWLEDGE_GRAPHS);
    }

    @Override
    public String getToolInfo() {
        return contextualAdviserComp.getContextMessage(Generative.DEEPFAKES) + "\n" +
               contextualAdviserComp.getContextMessage(ReinforcementLearning.AUTONOMOUS_DRIVING) + "\n" +
               contextualAdviserComp.getContextMessage(ComputerVision.IMAGE_CLASSIFICATION);
    }

    @Override
    public String getUserInfo() {
        return contextualAdviserComp.getContextMessage(RecommendationSystems.COLLABORATIVE_FILTERING) + "\n" +
               contextualAdviserComp.getContextMessage(PredictiveAnalytics.TIME_SERIES_FORECASTING) + "\n" +
               contextualAdviserComp.getContextMessage(Robotics.MOTION_CONTROL);
    }

    @Override
    public String getDataInfo() {
        return contextualAdviserComp.getContextMessage(Optimization.LINEAR_PROGRAMMING) + "\n" +
               contextualAdviserComp.getContextMessage(Recognition.FACIAL_RECOGNITION);
    }

    @Override
    public T updateContext(T userRequest, T aiResponse) {
        return contextualAdviserComp.updateContext(userRequest, aiResponse);
    }

    @Override
    public T processUserInput(T userRequest) {
        return contextualAdviserComp.processUserInput(userRequest);
    }

    @Override
    public T processAIOutput(T aiResponse) {
        return contextualAdviserComp.processAIOutput(aiResponse);
    }
}
