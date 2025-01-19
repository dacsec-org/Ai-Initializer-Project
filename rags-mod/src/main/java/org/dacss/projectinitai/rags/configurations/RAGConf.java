package org.dacss.projectinitai.rags.configurations;

import org.dacss.projectinitai.processors.components.VectorizationProcessorComp;

import org.dacss.projectinitai.rags.components.ConversationListerComp;
import org.dacss.projectinitai.rags.utilities.RAGCreatorUtil;
import org.dacss.projectinitai.rags.handlers.RAGHandler;
import org.dacss.projectinitai.rags.utilities.RAGClonerUtil;
import org.dacss.projectinitai.rags.utilities.RAGDestroyerUtil;
import org.dacss.projectinitai.rags.utilities.RAGUpdaterUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RAGConf {

    @Bean
    public RAGHandler ragHandler(RAGCreatorUtil creator, RAGDestroyerUtil destroyer
            , RAGClonerUtil cloner, RAGUpdaterUtil updater
            , ConversationListerComp lister, VectorizationProcessorComp vectorize) {
        return new RAGHandler(creator, destroyer, cloner, updater, lister, vectorize);
    }

    @Bean
    public RAGClonerUtil ragCloner() {
        return new RAGClonerUtil();
    }

    @Bean
    public RAGCreatorUtil ragCreator() {
        return new RAGCreatorUtil();
    }

    @Bean
    public RAGDestroyerUtil ragDestroyer() {
        return new RAGDestroyerUtil();
    }

    @Bean
    public ConversationListerComp ragConversationLister() {
        /*fixme: the bellow RagConversationLister() is looking for 3 args*/
        return null;
//        return new ConversationListerComp();
    }

    @Bean
    public RAGUpdaterUtil ragUpdater() {
        return new RAGUpdaterUtil();
    }

    @Bean
    public VectorizationProcessorComp vectorizationProcessor() { return new VectorizationProcessorComp(); }
}
