package org.dacss.projectinitai.loaders.configurations;
/**/
import org.dacss.projectinitai.loaders.kernels.DynamicModelLoaderKernel;
//import org.dacss.projectinitai.loaders.parallelized.ParallelizedModelLoader;
/**/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>{@link LoadersConf}</h1>
 * <p>
 *     Configuration class for the hardware accelerated loaders.
 * </p>
 */
@Configuration
public class LoadersConf {

    /**
     * {@link #dynamicModelLoaderKernel()} method.
     *
     * @return DynamicModelLoaderKernel - returns the dynamic model loader kernel.
     */
    @Bean
    public DynamicModelLoaderKernel dynamicModelLoaderKernel() {
        return new DynamicModelLoaderKernel();
    }


//    /**
//     * {@link #parallelizedModelLoader()} method.
//     *
//     * @return ParallelizedModelLoader - returns the parallelized model loader.
//     */
//    @Bean
//    public ParallelizedModelLoader parallelizedModelLoader() {
//        return new ParallelizedModelLoader();
//    }

}
