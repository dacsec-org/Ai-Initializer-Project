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
    POSTGRESQL_VECTOR;

    public String getContextMessage() {
        return switch (this) {
            case FAISS -> """
                    Your purpose is to interact with the FAISS vector database.
                    Focus on efficient similarity search and clustering of dense vectors.
                    """;
            case MILVUS -> """
                    Your purpose is to interact with the Milvus vector database.
                    Emphasize scalable and high-performance vector search.
                    """;
            case PINECONE -> """
                    Your purpose is to interact with the Pinecone vector database.
                    Focus on real-time vector similarity search and management.
                    """;
            case WEAVIATE -> """
                    Your purpose is to interact with the Weaviate vector database.
                    Emphasize semantic search and knowledge graph capabilities.
                    """;
            case QDRANT -> """
                    Your purpose is to interact with the Qdrant vector database.
                    Focus on high-performance and scalable vector search.
                    """;
            case REDIS_VECTOR -> """
                    Your purpose is to interact with the Redis vector database.
                    Focus on in-memory data structure store for vector similarity search.
                    """;
            case NEO4J -> """
                    Your purpose is to interact with the Vector4J vector database.
                    Emphasize high-performance vector operations and search.
                    """;
            case POSTGRESQL_VECTOR -> """
                    Your purpose is to interact with the PostgreSQL vector database.
                    Focus on vector similarity search within a relational database.
                    """;
        };
    }
}
