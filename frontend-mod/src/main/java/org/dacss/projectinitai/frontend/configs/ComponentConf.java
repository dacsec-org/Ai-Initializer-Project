package org.dacss.projectinitai.frontend.configs;
/**/
import org.dacss.projectinitai.advisers.components.ContextualAdviserComp;
import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
import org.dacss.projectinitai.processors.components.TextProcessorComp;
import org.dacss.projectinitai.loaders.kernels.DynamicModelLoaderKernel;
import org.dacss.projectinitai.loaders.LLMProcessorComp;
import org.dacss.projectinitai.loaders.parallelized.ParallelizedModelLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**/

/**
 * <h1>{@link ComponentConf}</h1>
 * <p>
 *     Configuration class for the components of the application.
 * </p>
 */
@Configuration
public class ComponentConf {

    /**
     * {@link #llmProcessorComp(TextProcessorComp)} method.
     *
     * @param textProcessor - the text processor component.
     * @return LLMProcessorComp - returns the LLM processor component.
     */
    @Bean
    public LLMProcessorComp llmProcessorComp(TextProcessorComp textProcessor) {
        return new LLMProcessorComp(
                new DynamicModelLoaderKernel(),
                new ParallelizedModelLoader(),
                new ContextualAdviserComp<>(),
                /* fixme: the bellow ProcessorFactoryComp(); is looking for 14 args */
                new ProcessorFactoryComp());
        //FIXME: ProcessorFactoryComp() is looking for 14 args
    }

    /**
     * {@link #dynamicModelLoaderKernel()} method.
     *
     * @return DynamicModelLoaderKernel - returns the dynamic model loader kernel.
     */

    @Bean
    public DynamicModelLoaderKernel dynamicModelLoaderKernel() {
        return new DynamicModelLoaderKernel();
    }


    /**
     * {@link #parallelizedModelLoader()} method.
     *
     * @return ParallelizedModelLoader - returns the parallelized model loader.
     */
    @Bean
    public ParallelizedModelLoader parallelizedModelLoader() {
        return new ParallelizedModelLoader();
    }

    /**
     * {@link #contextualAdviserComp()} method.
     *
     * @return ContextualAdviserComp - returns the contextual adviser component.
     */
    @Bean
    public ContextualAdviserComp<String> contextualAdviserComp() {
        return new ContextualAdviserComp<>();
    }
}
