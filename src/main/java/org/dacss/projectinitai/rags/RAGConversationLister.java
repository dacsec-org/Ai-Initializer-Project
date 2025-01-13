package org.dacss.projectinitai.rags;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.advisers.processors.JsonProcessor;
import org.springframework.ai.autoconfigure.vectorstore.redis.RedisVectorStoreProperties;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h1>{@link RAGConversationLister}</h1>
 * This class is used to query then list the conversation histories from the
 * redis database. This is a refactor of Spring's
 * {@link org.springframework.ai.vectorstore.redis.RedisVectorStore}
 * beer example.
 */
@Slf4j
@Component
public class RAGConversationLister implements ApplicationRunner {

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
    private final JsonProcessor jsonProcessor;

    public RAGConversationLister(RedisVectorStore store, RedisVectorStoreProperties properties, JsonProcessor jsonProcessor) {
        this.store = store;
        this.properties = properties;
        this.jsonProcessor = jsonProcessor;
    }

    /**
     * Queries redis database conversation histories.
     * @param source The source to query conversation histories from.
     */

    @SneakyThrows
    public List<String> listChatHistory(String source) {
        log.info("Querying conversation histories from source: {}", source);
        List<String> rawHistories = store.getJedis().lrange(source, 0, -1);
        return rawHistories.stream()
                .map(jsonProcessor::processString)
                .toList();
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
