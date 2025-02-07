package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.databases.DataBaseIface;
import org.dacss.projectinitai.databases.DataBaseTypes;
import org.dacss.projectinitai.databases.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DataBaseService}</h1>
 * Hilla endpoint service for switching database functionality.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class DataBaseService implements DataBaseIface {

    private static final Logger log = LoggerFactory.getLogger(DataBaseService.class);

    @Override
    public Flux<Object> performDatabaseAction(DataBaseTypes type) {
        Flux<Object> flux;
        try {
            flux = switch (type) {
                case FAISS -> new FaissUtil().handleFaiss();
                case MILVUS -> new MilvusUtil().handleMilvus();
                case PINECONE -> new PineconeUtil().handlePinecone();
                case WEAVIATE -> new WeaviateUtil().handleWeaviate();
                case QDRANT -> new QdrantUtil().handleQdrant();
                case REDIS_VECTOR -> new RedisVectorUtil().handleRedisVector();
                case NEO4J -> new Neo4JUtil().handleNeo4J();
                case POSTGRESQL_VECTOR -> new PostgreSQLVectorUtil().handlePostgreSQLVector();
                case H_2 -> new H2Util().handleH2().cast(Object.class);
            };
            log.info("{}: Database operation completed: {}", type, flux);
        } catch (IllegalArgumentException databaseServiceExc) {
            log.error("{}: Error handling operation:", type, databaseServiceExc);
            return Flux.empty();
        }
        assert flux != null;
        return flux;
    }
}
