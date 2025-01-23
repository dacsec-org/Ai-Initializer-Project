package org.dacss.projectinitai.admin.services;

import org.dacss.projectinitai.admin.handlers.ContextsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ContextsService}</h1>
 * <p>
 *     Service class for the Context messages.
 * </p>
 */
@Service
public class ContextsService {

    private final ContextsHandler<String> contextsHandler;

    @Autowired
    public ContextsService(ContextsHandler<String> contextsHandler) {
        this.contextsHandler = contextsHandler;
    }

    /**
     * This method handles the context type and returns the context message.
     * @param contextType The type of context.
     * @param userRequest The user request.
     * @param aiResponse The AI response.
     */
    public void handleContextType(String contextType, String userRequest, String aiResponse) {
        switch (contextType) {
            case "GENERATIVE":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "KNOWLEDGE_REPRESENTATION_REASONING":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "NATURAL_LANGUAGE_PROCESSING":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "OPTIMIZATION":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "PREDICTIVE_ANALYTICS":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "RECOGNITION":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "RECOMMENDATION_SYSTEMS":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "REINFORCEMENT_LEARNING":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "ROBOTICS":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "SPEECH_RECOGNITION":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            case "COMPUTER_VISION":
                contextsHandler.updateContext(userRequest, aiResponse);
                break;
            default:
                throw new IllegalArgumentException(STR."Unknown context type: \{contextType}");
        }
    }
}
