package org.dacss.projectinitai.advisers.contexts;

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

    private final ContextualAdviserComp<T> CAC;

    public ContextFacadeIfaceImpl(ContextualAdviserComp<T> CAC) {
        this.CAC = CAC;
    }

    @Override
    public String getSystemInfo() {
        return CAC.getContextMessage(NaturalLanguageProcessing.TEXT_GENERATION) + "\n" +
               CAC.getContextMessage(SpeechRecognition.SPEECH_TO_TEXT) +
                "\n" +
               CAC.getContextMessage(KnowledgeRepresentationReasoning.KNOWLEDGE_GRAPHS);
    }

    @Override
    public String getToolInfo() {
        return CAC.getContextMessage(Generative.DEEPFAKES) + "\n" +
               CAC.getContextMessage(ReinforcementLearning.AUTONOMOUS_DRIVING) + "\n" +
               CAC.getContextMessage(ComputerVision.IMAGE_CLASSIFICATION);
    }

    @Override
    public String getUserInfo() {
        return CAC.getContextMessage(RecommendationSystems.COLLABORATIVE_FILTERING) + "\n" +
               CAC.getContextMessage(PredictiveAnalytics.TIME_SERIES_FORECASTING) + "\n" +
               CAC.getContextMessage(Robotics.MOTION_CONTROL);
    }

    @Override
    public String getDataInfo() {
        return CAC.getContextMessage(Optimization.LINEAR_PROGRAMMING) + "\n" +
               CAC.getContextMessage(Recognition.FACIAL_RECOGNITION);
    }

    @Override
    public T updateContext(T userRequest, T aiResponse) {
        return CAC.updateContext(userRequest, aiResponse);
    }

    @Override
    public T processUserInput(T userRequest) {
        return CAC.processUserInput(userRequest);
    }

    @Override
    public T processAIOutput(T aiResponse) {
        return CAC.processAIOutput(aiResponse);
    }
}
