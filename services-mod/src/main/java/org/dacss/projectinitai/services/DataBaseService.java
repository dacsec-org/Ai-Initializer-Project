package org.dacss.projectinitai.services;

import org.dacss.projectinitai.annotations.Bridge;
import org.dacss.projectinitai.databases.DBActions;
import org.dacss.projectinitai.databases.DataBaseIface;
import org.dacss.projectinitai.databases.DBTypes;
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
@Bridge("database-service")
public class DataBaseService implements DataBaseIface {

    private static final Logger log = LoggerFactory.getLogger(DataBaseService.class);

    @Override
    public Flux<Object> performDatabaseAction(DBTypes type, DBActions action, Object data) {
        Flux<Object> flux;
        try {
            flux = switch (type) {
                case FAISS -> new FaissDB().handleFaiss();
                case MILVUS -> new MilvusDB().handleMilvus();
                case PINECONE -> new PineconeDB().handlePinecone();
                case WEAVIATE -> new WeaviateDB().handleWeaviate();
                case QDRANT -> new QdrantDB().handleQdrant();
                case REDIS_VECTOR -> new RedisVectorDB().handleRedisVector();
                case NEO4J -> new Neo4JDB().handleNeo4J();
                case POSTGRESQL_VECTOR -> new PostgreSQLVectorDB().handlePostgreSQLVector();
                case H_2 -> new H2DB().handleH2().cast(Object.class);
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
