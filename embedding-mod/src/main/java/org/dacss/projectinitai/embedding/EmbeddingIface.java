package org.dacss.projectinitai.embedding;
/**/

/**
 * <h1>{@link EmbeddingIface}</h1>
 */
@FunctionalInterface
public interface EmbeddingIface {
    /**
     * <h2>{@link #processEmbedding(String action, String data)}</h2>
     */
    void processEmbedding(String action, String data);
}
