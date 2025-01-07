package org.dacss.projectinitai.clients;

import org.dacss.projectinitai.advisers.*;
import org.dacss.projectinitai.advisers.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversalChatClient<T> {

    private final PreProcessingAdviserIface<T> preProcessingAdviser;
    private final PostProcessingAdviserIface<T> postProcessingAdviser;
    private final DomainSpecificAdviserIface<T> domainSpecificAdviser;
    private final ContextualAdviserIface<T> contextualAdviser;

    @Autowired
    public UniversalChatClient(PreProcessingAdviserIface<T> preProcessingAdviser,
                               PostProcessingAdviserIface<T> postProcessingAdviser,
                               DomainSpecificAdviserIface<T> domainSpecificAdviser,
                               ContextualAdviserIface<T> contextualAdviser) {
        this.preProcessingAdviser = preProcessingAdviser;
        this.postProcessingAdviser = postProcessingAdviser;
        this.domainSpecificAdviser = domainSpecificAdviser;
        this.contextualAdviser = contextualAdviser;
    }

    public T sendMessage(T message) {
        // Pre-process the message
        T preProcessedMessage = preProcessingAdviser.preProcess(message);

        // Add domain-specific expertise and send the message to the LLM model
        T postProcessedResponse = postProcessingAdviser.postProcess(
            domainSpecificAdviser.provideExpertise(preProcessedMessage)
        );

        // Update context using method reference
        contextualAdviser.updateContext(preProcessedMessage, postProcessedResponse);

        return postProcessedResponse;
    }

    public String getContext() {
        return ((ContextualAdviserComponent<T>) contextualAdviser).getContext();
    }
}
