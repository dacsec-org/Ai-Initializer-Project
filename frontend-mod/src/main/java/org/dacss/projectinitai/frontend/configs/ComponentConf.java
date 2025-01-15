package org.dacss.projectinitai.frontend.configs;

import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
import org.dacss.projectinitai.loaders.components.ContextualAdviserComp;
import org.dacss.projectinitai.processors.interfaces.ByteProcessingAdviserIface;
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
import org.dacss.projectinitai.loaders.kernels.DynamicModelLoaderKernel;
import org.dacss.projectinitai.loaders.LLMProcessorComp;
import org.dacss.projectinitai.loaders.parallelized.ParallelizedModelLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConf {

    @Bean
    public LLMProcessorComp llmProcessorComp() {
        return new LLMProcessorComp(
                new DynamicModelLoaderKernel(),
                new ParallelizedModelLoader(),
                new ContextualAdviserComp<>(),
                new ProcessorFactoryComp());

    }

    @Bean
    public DynamicModelLoaderKernel dynamicModelLoaderKernel() {
        return new DynamicModelLoaderKernel();
    }

    @Bean
    public ParallelizedModelLoader parallelizedModelLoader() {
        return new ParallelizedModelLoader();
    }


    @Bean
    public ContextualAdviserComp<String> contextualAdviserComp() {
        return new ContextualAdviserComp<>();
    }
}
