package org.dacss.projectinitai.databases;

/**
 * <h1>{@link DataBaseTypes}</h1>
 * Enum for different types of vector databases.
 */
public enum DataBaseTypes {
    FAISS,
    MILVUS,
    PINECONE,
    WEAVIATE,
    QDRANT,
    REDIS_VECTOR,
    NEO4J,
    POSTGRESQL_VECTOR,
    H2;
}
