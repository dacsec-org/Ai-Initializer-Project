package org.dacss.projectinitai.databases;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link DataBaseService}</h1>
 * Backend hilla endpoint service for vector database operations.
 */
@Service
@BrowserCallable
public class DataBaseService {

    private DataBaseHandler handler;

    /**
     * <h2>{@link #DataBaseService()}</h2>
     * 0-arg constructor to instantiate the {@link DataBaseHandler}.
     */
    public DataBaseService() {
        this.handler = new DataBaseHandler();
    }

    /**
     * <h2>{@link #handleDataBaseAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleDataBaseAction(String action, String data) {
        return switch (DataBaseTypes.valueOf(action.toUpperCase())) {
            case FAISS -> handler.handleFaiss(data);
            case MILVUS -> handler.handleMilvus(data);
            case PINECONE -> handler.handlePinecone(data);
            case WEAVIATE -> handler.handleWeaviate(data);
            case QDRANT -> handler.handleQdrant(data);
            case REDIS_VECTOR -> handler.handleRedisVector(data);
            case NEO4J -> handler.handleNeo4J(data);
            case POSTGRESQL_VECTOR -> handler.handlePostgreSQLVector(data);
        };
    }
}
