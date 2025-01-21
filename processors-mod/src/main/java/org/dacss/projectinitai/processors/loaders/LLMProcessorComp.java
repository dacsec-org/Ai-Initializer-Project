package org.dacss.projectinitai.processors.loaders;
/**/
import org.dacss.projectinitai.contexts.ContextsHandler;
import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
/**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LLMProcessorComp {

    private final ContextsHandler<String> contextsHandler;
    private final ProcessorFactoryComp processorFactoryComp;

    @Autowired
    public LLMProcessorComp(
            ContextsHandler<String> contextsHandler
            , ProcessorFactoryComp processorFactoryComp) {
        this.contextsHandler = contextsHandler;
        this.processorFactoryComp = processorFactoryComp;
    }
}

