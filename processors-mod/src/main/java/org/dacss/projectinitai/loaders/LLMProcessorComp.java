package org.dacss.projectinitai.loaders;
/**/
import org.dacss.projectinitai.advisers.components.ContextualAdviserComp;
import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
import org.dacss.projectinitai.processors.enums.MessageType;
/**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

