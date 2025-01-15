package org.dacss.projectinitai.loaders.components;


import com.vaadin.flow.component.notification.Notification;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.contexts.generative.Generative;
import org.dacss.projectinitai.contexts.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.contexts.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.contexts.optimization.Optimization;
import org.dacss.projectinitai.contexts.predictive.PredictiveAnalytics;
import org.dacss.projectinitai.contexts.recomondation.RecommendationSystems;
import org.dacss.projectinitai.contexts.reinforcement.ReinforcementLearning;
import org.dacss.projectinitai.contexts.robotics.Robotics;
import org.dacss.projectinitai.contexts.speech.SpeechRecognition;
import org.dacss.projectinitai.contexts.vision.ComputerVision;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link ContextualAdviserComp}</h1>
 * @param <T>
 */
@Slf4j
@Component
public class ContextualAdviserComp<T> {

    private final StringBuilder context = new StringBuilder();
    @Getter
    private T lastUserRequest;
    @Getter
    private T lastAIResponse;

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
            Notification.show("Error updating context: " + e.getMessage());
            return null;
        }
    }

    public T processUserInput(T userRequest) {
        try {
            log.info("Processing user input: {}", userRequest);
            return userRequest;
        } catch (Exception e) {
            log.error("Error processing user input: ", e);
            Notification.show("Error processing user input: " + e.getMessage());
            return null;
        }
    }

    public T processAIOutput(T aiResponse) {
        try {
            log.info("Processing AI output: {}", aiResponse);
            return aiResponse;
        } catch (Exception e) {
            log.error("Error processing AI output: ", e);
            Notification.show("Error processing AI output: " + e.getMessage());
            return null;
        }
    }

    public String getContextMessage(Enum<?> contextType) {
        return switch (contextType) {
            case NaturalLanguageProcessing naturalLanguageProcessing ->
                    "NLP Context: " + contextType.name();
            case RecommendationSystems recommendationSystems ->
                    "Recommendation Systems Context: " + contextType.name();
            case Generative generative ->
                    "Generative Context: " + contextType.name();
            case Optimization optimization ->
                    "Optimization Context: " + contextType.name();
            case ComputerVision computerVision ->
                    "Computer Vision Context: " + contextType.name();
            case Robotics robotics ->
                    "Robotics Context: " + contextType.name();
            case KnowledgeRepresentationReasoning knowledgeRepresentationReasoning ->
                    "Knowledge Representation Reasoning Context: " + contextType.name();
            case PredictiveAnalytics predictiveAnalytics ->
                    "Predictive Analytics Context: " + contextType.name();
            case ReinforcementLearning reinforcementLearning ->
                    "Reinforcement Learning Context: " + contextType.name();
            case SpeechRecognition speechRecognition ->
                    "Speech Recognition Context: " + contextType.name();
            case null, default -> {
                assert contextType != null;
                yield "Unknown Context: " + contextType.name();
            }
        };
    }

    public String getContext() {
        return context.toString();
    }

    public void clearContext() {
        context.setLength(0);
        log.info("Context cleared.");
        Notification.show("Context cleared.");
    }

    public void addCustomContextEntry(String entry) {
        context.append(entry).append("\n");
        log.info("Custom context entry added: {}", entry);
        Notification.show("Custom context entry added: " + entry);
    }
}
