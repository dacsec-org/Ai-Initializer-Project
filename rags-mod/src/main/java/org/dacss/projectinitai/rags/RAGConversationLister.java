package org.dacss.projectinitai.rags;



import org.dacss.projectinitai.processors.components.JsonProcessorComp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.autoconfigure.vectorstore.redis.RedisVectorStoreProperties;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RAGConversationLister implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(RAGConversationLister.class);
    /**
     * The keys used to query the redis database for conversation history.
     */
    private static final String[] KEYS = { "history", "chatName:*" };

    /**
     * The data resource used to query the redis database for conversation history.
     */
    @Value("classpath:/data/chats.json")
    //todo: change the classpath and file name
    private Resource data;

    /**
     * The vector store used to query the redis database for conversation history.
     */
    private final RedisVectorStore store;

    /**
     * The vector store properties used to query the redis database for conversation history.
     */
    private final RedisVectorStoreProperties properties;

    /**
     * The JSON processor used to process JSON data.
     */
    private final JsonProcessorComp jsonProcessor;

    public RAGConversationLister(RedisVectorStore store,
                                 RedisVectorStoreProperties properties,
                                 JsonProcessorComp jsonProcessor) {
        this.store = store;
        this.properties = properties;
        this.jsonProcessor = jsonProcessor;
    }

    /**
     * Queries redis database conversation histories.
     * @param source The source to query conversation histories from.
     */
    public List<String> listChatHistory(String source) {
        log.info("Querying conversation histories from source: {}", source);
        List<String> rawHistories = store.getJedis().lrange(source, 0, -1);
        List<String> list = new ArrayList<>();
        for (String rawHistory : rawHistories) {
            String s = jsonProcessor.processString(rawHistory);
            list.add(s);
        }
        return list;
    }

    /**
     * Callback used to run the bean.
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (String key : KEYS) {
            List<String> chatHistories = store.getJedis().lrange(key, 0, -1);
            for (String chat : chatHistories) {
                String processedChat = jsonProcessor.processString(chat);
                log.info("Chat: {}", processedChat);
            }
        }
    }
}
