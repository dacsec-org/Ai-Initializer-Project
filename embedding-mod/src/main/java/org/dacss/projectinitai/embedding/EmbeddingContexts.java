package org.dacss.projectinitai.embedding;

/**
 * <h1>{@link EmbeddingContexts}</h1>
 * Enum class representing the different types of AI-LLM embedding models.
 * Each enum constant has a context message that provides a brief description of the purpose of the embedding model.
 */
public enum EmbeddingContexts {
    WORD2VEC,
    GLOVE,
    FASTTEXT,
    BERT,
    GPT,
    TRANSFORMER;

    public String getContextMessage() {
        return switch (this) {
            case WORD2VEC -> """
                    Your purpose is to create word embeddings using the Word2Vec model.
                    Focus on capturing semantic relationships between words.
                    """;
            case GLOVE -> """
                    Your purpose is to generate word embeddings using the GloVe model.
                    Emphasize capturing global statistical information from the text corpus.
                    """;
            case FASTTEXT -> """
                    Your purpose is to produce word embeddings using the FastText model.
                    Focus on capturing subword information and handling out-of-vocabulary words.
                    """;
            case BERT -> """
                    Your purpose is to create contextualized word embeddings using the BERT model.
                    Emphasize understanding the context of words in sentences.
                    """;
            case GPT -> """
                    Your purpose is to generate embeddings using the GPT model.
                    Focus on capturing long-range dependencies and generating coherent text.
                    """;
            case TRANSFORMER -> """
                    Your purpose is to create embeddings using the Transformer model.
                    Emphasize capturing complex patterns and relationships in the data.
                    """;
        };
    }
}
