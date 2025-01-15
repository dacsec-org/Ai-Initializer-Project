package org.dacss.projectinitai.loaders;

import org.dacss.projectinitai.loaders.components.ContextualAdviserComp;
import org.dacss.projectinitai.loaders.parallelized.ParallelizedModelLoader;
import org.dacss.projectinitai.loaders.kernels.DynamicModelLoaderKernel;
import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
import org.dacss.projectinitai.processors.enums.MessageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LLMProcessorComp {
    private final DynamicModelLoaderKernel dynamicModelLoaderKernel;
    private final ParallelizedModelLoader parallelizedModelLoader;
    private final ContextualAdviserComp<String> contextualAdviserComp;
    private final ProcessorFactoryComp processorFactoryComp;

    @Autowired
    public LLMProcessorComp(
            DynamicModelLoaderKernel dynamicModelLoaderKernel
            , ParallelizedModelLoader parallelizedModelLoader
            , ContextualAdviserComp<String> contextualAdviserComp
            , ProcessorFactoryComp processorFactoryComp) {
        this.dynamicModelLoaderKernel = dynamicModelLoaderKernel;
        this.parallelizedModelLoader = parallelizedModelLoader;
        this.contextualAdviserComp = contextualAdviserComp;
        this.processorFactoryComp = processorFactoryComp;
    }

    public void process(String input) throws IOException {
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
