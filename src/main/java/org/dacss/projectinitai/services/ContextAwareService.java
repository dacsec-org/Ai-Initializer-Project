package org.dacss.projectinitai.services;

import org.dacss.projectinitai.components.ContextualAdviserComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextAwareService {

    private final ContextualAdviserComp<String> contextualAdviserComp;

    @Autowired
    public ContextAwareService(ContextualAdviserComp<String> contextualAdviserComp) {
        this.contextualAdviserComp = contextualAdviserComp;
    }

    public String handleUserRequest(String userRequest) {
        String processedUserInput = contextualAdviserComp.processUserInput(userRequest);
        // Simulate AI response
        String aiResponse = "AI response to: " + processedUserInput;
        String processedAIOutput = contextualAdviserComp.processAIOutput(aiResponse);
        return contextualAdviserComp.updateContext(processedUserInput, processedAIOutput);
    }

    public String getContext() {
        return contextualAdviserComp.getContext();
    }
}
