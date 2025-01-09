package org.dacss.projectinitai.advisers.contexts;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContextualAdviserComponent<T> implements ContextualAdviserIface<T>, UserInputContextualAdviserIface<T>, AIOutputContextualAdviserIface<T>, DataHandlerContextualAdviserIface<T> {

    private final StringBuilder context = new StringBuilder();
    @Getter
    private T lastUserRequest;
    @Getter
    private T lastAIResponse;

    @Override
    public T updateContext(T userRequest, T aiResponse) {
        try {
            lastUserRequest = userRequest;
            lastAIResponse = aiResponse;
            context.append("USER: ").append(userRequest.toString()).append("\n");
            context.append("AI: ").append(aiResponse.toString()).append("\n");
            log.info("Context updated with user request and AI response.");
            return userRequest;
        } catch (Exception e) {
            log.error("Error updating context: ", e);
            return null;
        }
    }

    @Override
    public T processUserInput(T userRequest) {
        try {
            log.info("Processing user input: {}", userRequest);
            return userRequest;
        } catch (Exception e) {
            log.error("Error processing user input: ", e);
            return null;
        }
    }

    @Override
    public T processAIOutput(T aiResponse) {
        try {
            log.info("Processing AI output: {}", aiResponse);
            return aiResponse;
        } catch (Exception e) {
            log.error("Error processing AI output: ", e);
            return null;
        }
    }

    @Override
    public String getNaturalLanguageProcessingContext(NaturalLanguageProcessing nlp) {
        return "NLP Context: " + nlp.name();
    }

    @Override
    public String getRecommendationSystemsContext(RecommendationSystems recommendationSystems) {
        return "Recommendation Systems Context: " + recommendationSystems.name();
    }

    @Override
    public String getGenerativeContext(Generative generative) {
        return "Generative Context: " + generative.name();
    }

    @Override
    public String getOptimizationContext(Optimization optimization) {
        return "Optimization Context: " + optimization.name();
    }

    @Override
    public String getComputerVisionContext(ComputerVision computerVision) {
        return "Computer Vision Context: " + computerVision.name();
    }

    @Override
    public String getRoboticsContext(Robotics robotics) {
        return "Robotics Context: " + robotics.name();
    }

    @Override
    public String getKnowledgeRepresentationReasoningContext(KnowledgeRepresentationReasoning krr) {
        return "Knowledge Representation Reasoning Context: " + krr.name();
    }

    @Override
    public String getPredictiveAnalyticsContext(PredictiveAnalytics predictiveAnalytics) {
        return "Predictive Analytics Context: " + predictiveAnalytics.name();
    }

    public String getContext() {
        return context.toString();
    }

    public void clearContext() {
        context.setLength(0);
        log.info("Context cleared.");
    }

    public void addCustomContextEntry(String entry) {
        context.append(entry).append("\n");
        log.info("Custom context entry added: {}", entry);
    }
}
