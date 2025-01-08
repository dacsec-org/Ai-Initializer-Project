package org.dacss.projectinitai.clients;

import org.dacss.projectinitai.advisers.contexts.ContextualAdviserComponent;
import org.dacss.projectinitai.advisers.contexts.ContextualAdviserIface;
import org.dacss.projectinitai.advisers.domains.DomainSpecificAdviserIface;
import org.dacss.projectinitai.advisers.processors.post.PostProcessingAdviserIface;
import org.dacss.projectinitai.advisers.processors.ProcessingAdviserIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversalChatClient<T> {

    private final ProcessingAdviserIface<T> preProcessingAdviser;
    private final DomainSpecificAdviserIface<T> domainSpecificAdviser;
    private final ContextualAdviserIface<T> contextualAdviser;

    @Autowired
    public UniversalChatClient(ProcessingAdviserIface<T> preProcessingAdviser,
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
