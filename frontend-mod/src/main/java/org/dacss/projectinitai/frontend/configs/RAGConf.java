package org.dacss.projectinitai.frontend.configs;

import org.dacss.projectinitai.processors.components.VectorizationProcessorComp;
import org.dacss.projectinitai.rags.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RAGConf {

    @Bean
    public RAGHandler ragHandler(RAGCreator creator, RAGDestroyer destroyer, RAGCloner cloner, RAGUpdater updater, RAGConversationLister lister, VectorizationProcessorComp vectorize) {
        return new RAGHandler(creator, destroyer, cloner, updater, lister, vectorize);
    }

    @Bean
    public RAGCloner ragCloner() {
        return new RAGCloner();
    }

    @Bean
    public RAGCreator ragCreator() {
        return new RAGCreator();
    }

    @Bean
    public RAGDestroyer ragDestroyer() {
        return new RAGDestroyer();
    }

    @Bean
    public RAGConversationLister ragConversationLister() {
        /*fixme: the bellow RagConversationLister() is looking for 3 args*/
        return new RAGConversationLister();
    }

    @Bean
    public RAGUpdater ragUpdater() {
        return new RAGUpdater();
    }

    @Bean
    public VectorizationProcessorComp vectorizationProcessor() {
        return new VectorizationProcessorComp();
    }
}
