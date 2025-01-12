package org.dacss.projectinitai.loaders;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.components.ContextualAdviserComp;
import org.dacss.projectinitai.components.ProcessorFactoryComp;
import org.dacss.projectinitai.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LLMProcessorComp {
    private final DynamicModelLoaderKernel dynamicModelLoaderKernel;
    private final ParallelizedModelLoader parallelizedModelLoader;
    private final ContextualAdviserComp<String> contextualAdviserComp;
    private final ProcessorFactoryComp processorFactoryComp;

    @Autowired
    public LLMProcessorComp(DynamicModelLoaderKernel dynamicModelLoaderKernel, ParallelizedModelLoader parallelizedModelLoader,
                            ContextualAdviserComp<String> contextualAdviserComp,
                            ProcessorFactoryComp processorFactoryComp) {
        this.dynamicModelLoaderKernel = dynamicModelLoaderKernel;
        this.parallelizedModelLoader = parallelizedModelLoader;
        this.contextualAdviserComp = contextualAdviserComp;
        this.processorFactoryComp = processorFactoryComp;
    }

    public void process(String input) {
        // Use the loaded model from DynamicModelLoaderKernel to process the input
        byte[] model = dynamicModelLoaderKernel.getModel();
        // Process the input using the model
    }

    public void integrateWithContextualAdviser(String input) {
        // Use ParallelizedModelLoader to handle IO looping operations
        parallelizedModelLoader.process(input);
        contextualAdviserComp.processUserInput(input);
    }

    public void integrateWithProcessorFactory(String input) {
        // Use ParallelizedModelLoader to handle IO looping operations
        parallelizedModelLoader.process(input);
        processorFactoryComp.getStringProcessor(MessageType.valueOf(input));
    }
}
