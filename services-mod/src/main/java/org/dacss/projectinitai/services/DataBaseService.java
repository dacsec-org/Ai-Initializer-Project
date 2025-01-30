package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.databases.DataBaseIface;
import org.dacss.projectinitai.databases.DataBaseTypes;
import org.dacss.projectinitai.databases.utilities.*;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

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
     * <h2>{@link #performDatabaseAction(String)}</h2>
     *
     * @param action String selection of the database action to perform.
     */
    @Override
    public void performDatabaseAction(String action) {
        try {
            Flux<Object> flux;
            DataBaseTypes dataBaseTypes = DataBaseTypes.valueOf(action.toUpperCase());
            switch (dataBaseTypes) {
                case FAISS:
                    log.info("Performing Faiss action...");
                    flux = new FaissUtil().handleFaiss();
                    break;
                case MILVUS:
                    log.info("Performing Milvus action...");
                    flux = new MilvusUtil().handleMilvus();
                    break;
                case PINECONE:
                    log.info("Performing Pinecone action...");
                    flux = new PineconeUtil().handlePinecone();
                    break;
                case WEAVIATE:
                    log.info("Performing Weaviate action...");
                    flux = new WeaviateUtil().handleWeaviate();
                    break;
                case QDRANT:
                    log.info("Performing Qdrant action...");
                    flux = new QdrantUtil().handleQdrant();
                    break;
                case REDIS_VECTOR:
                    log.info("Performing Redis Vector action...");
                    flux = new RedisVectorUtil().handleRedisVector();
                    break;
                case NEO4J:
                    log.info("Performing Neo4J action...");
                    flux = new Neo4JUtil().handleNeo4J();
                    break;
                case POSTGRESQL_VECTOR:
                    log.info("Performing PostgreSQL Vector action...");
                    flux = new PostgreSQLVectorUtil().handlePostgreSQLVector();
                    break;
                case H2:
                    log.info("Performing H2 action...");
                    flux = new H2Util().handleH2().cast(Object.class);
                    break;
                default:
                    log.error("Invalid action: {}", action);
                    return;
            }
            assert flux != null;
            flux.subscribe(new CoreSubscriber<>() {
                @Override
                public void onSubscribe(Subscription subscription) { subscription.request(1); }

                @Override
                public void onNext(Object value) { log.info("{}: {}", action, value); }

                @Override
                public void onError(Throwable throwable) { log.error("Error performing action: {}", action, throwable); }

                @Override
                public void onComplete() { log.info("Action completed successfully: {}", action); }
            });
        } catch (IllegalArgumentException e) {
            log.error("Invalid action: {}", action, e);
        }
    }
}
