package org.dacss.projectinitai.advisers.contexts;

import org.dacss.projectinitai.advisers.contexts.ContextualAdviserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextAwareService {

    private final ContextualAdviserComponent<String> contextualAdviserComponent;

    @Autowired
    public ContextAwareService(ContextualAdviserComponent<String> contextualAdviserComponent) {
        this.contextualAdviserComponent = contextualAdviserComponent;
    }

    public String handleUserRequest(String userRequest) {
        String processedUserInput = contextualAdviserComponent.processUserInput(userRequest);
        // Simulate AI response
        String aiResponse = "AI response to: " + processedUserInput;
        String processedAIOutput = contextualAdviserComponent.processAIOutput(aiResponse);
        return contextualAdviserComponent.updateContext(processedUserInput, processedAIOutput);
    }

    public String getContext() {
        return contextualAdviserComponent.getContext();
    }
}
