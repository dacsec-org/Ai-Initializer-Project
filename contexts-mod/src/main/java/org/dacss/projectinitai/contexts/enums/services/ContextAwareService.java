package org.dacss.projectinitai.contexts.enums.services;
/**/
import org.dacss.projectinitai.contexts.advisers.components.ContextualAdviserComp;
/**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ContextAwareService}</h1>
 */
@Service
public class ContextAwareService {
//FIXME: this class is not being used. implement it, or 86 it.
    private final ContextualAdviserComp<String> contextualAdviserComp;

    @Autowired
    public ContextAwareService(ContextualAdviserComp<String> contextualAdviserComp) {
        this.contextualAdviserComp = contextualAdviserComp;
    }

    public String handleUserRequest(String userRequest) {
        String processedUserInput = contextualAdviserComp.processUserInput(userRequest);
        // Simulate AI response
        String aiResponse = STR."AI response to: \{processedUserInput}";
        String processedAIOutput = contextualAdviserComp.processAIOutput(aiResponse);
        return contextualAdviserComp.updateContext(processedUserInput, processedAIOutput);
    }

    public String getContext() {
        return contextualAdviserComp.getContext();
    }
}
