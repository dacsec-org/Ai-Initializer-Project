package org.dacss.projectinitai.configs;

import org.dacss.projectinitai.advisers.processors.JsonProcessor;
import org.dacss.projectinitai.advisers.processors.VectorizationProcessor;
import org.dacss.projectinitai.rags.*;
import org.springframework.ai.autoconfigure.vectorstore.redis.RedisVectorStoreProperties;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RAGConf {

    @Bean
    public RAGHandler ragHandler(RAGCreator creator,
                                 RAGDestroyer destroyer, RAGCloner cloner, RAGUpdater updater, RAGConversationLister lister, VectorizationProcessor vectorize) {
        return new RAGHandler(creator, destroyer, cloner, updater,
                lister, vectorize);
    }

    @Bean
    public RAGCloner ragCloner() {
        return new RAGCloner();
    }

    @Bean
    public RAGDestroyer ragDestroyer() {
        return new RAGDestroyer();
    }

    @Bean
    public RAGCreator ragCreator() {
        return new RAGCreator();
    }

    @Bean
    public RAGUpdater ragUpdater() {
        return new RAGUpdater();
    }

    @Bean
    public VectorizationProcessor vectorizationProcessor() {
        return new VectorizationProcessor();
    }
}
