package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.databases.DataBaseIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link DataBaseService}</h1>
 * Backend hilla endpoint service for vector database operations.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class DataBaseService implements DataBaseIface {


    private static final Logger log = LoggerFactory.getLogger(DataBaseService.class);

    /**
     * <h2>{@link #DataBaseService()}</h2>
     */
    public DataBaseService() {

    }

    /**
     * <h2>{@link #performDatabaseAction()}</h2>
     */
    @Override
    public void performDatabaseAction() {

    }
}

//    /**
//     * <h2>{@link #handleDataBaseAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be processed.
//     * @return The result of the action.
//     */
//    public Object handleDataBaseAction(String action, String data) {
//        return switch (DataBaseTypes.valueOf(action.toUpperCase())) {
//            case FAISS -> handler.handleFaiss(data);
//            case MILVUS -> handler.handleMilvus(data);
//            case PINECONE -> handler.handlePinecone(data);
//            case WEAVIATE -> handler.handleWeaviate(data);
//            case QDRANT -> handler.handleQdrant(data);
//            case REDIS_VECTOR -> handler.handleRedisVector(data);
//            case NEO4J -> handler.handleNeo4J(data);
//            case POSTGRESQL_VECTOR -> handler.handlePostgreSQLVector(data);
//        };
//    }
//}
