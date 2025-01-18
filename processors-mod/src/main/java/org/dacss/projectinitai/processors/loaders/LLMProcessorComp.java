package org.dacss.projectinitai.processors.loaders;
/**/
import org.dacss.projectinitai.contexts.advisers.components.ContextualAdviserComp;
import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
/**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LLMProcessorComp {

    private final ContextualAdviserComp<String> contextualAdviserComp;
    private final ProcessorFactoryComp processorFactoryComp;

    @Autowired
    public LLMProcessorComp(
            ContextualAdviserComp<String> contextualAdviserComp
            , ProcessorFactoryComp processorFactoryComp) {
        this.contextualAdviserComp = contextualAdviserComp;
        this.processorFactoryComp = processorFactoryComp;
    }
}

