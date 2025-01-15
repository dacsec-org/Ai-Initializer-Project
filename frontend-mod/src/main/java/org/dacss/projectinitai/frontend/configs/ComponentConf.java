package org.dacss.projectinitai.frontend.configs;

import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
import org.dacss.projectinitai.loaders.components.ContextualAdviserComp;
import org.dacss.projectinitai.processors.components.TextProcessorComp;
import org.dacss.projectinitai.loaders.kernels.DynamicModelLoaderKernel;
import org.dacss.projectinitai.loaders.LLMProcessorComp;
import org.dacss.projectinitai.loaders.parallelized.ParallelizedModelLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConf {

    @Bean
    public LLMProcessorComp llmProcessorComp(TextProcessorComp textProcessor) {
        return new LLMProcessorComp(
                new DynamicModelLoaderKernel(),
                new ParallelizedModelLoader(),
                new ContextualAdviserComp<>(),
                /* fixme: the bellow ProcessorFactoryComp(); is looking for 14 args */
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
