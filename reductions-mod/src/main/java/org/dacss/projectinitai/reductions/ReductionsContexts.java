package org.dacss.projectinitai.reductions;

/**
 * <h1>{@link ReductionsContexts}</h1>
 * Enum class representing the different types of Dimensionality Reduction techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Dimensionality Reduction technique.
 */
public enum ReductionsContexts {
    PRINCIPAL_COMPONENT_ANALYSIS,
    LINEAR_DISCRIMINANT_ANALYSIS,
    FACTOR_ANALYSIS,
    T_SNE,
    UMAP;

    public String getContextMessage() {
        return switch (this) {
            case PRINCIPAL_COMPONENT_ANALYSIS -> """
                    Your purpose is to reduce the dimensionality of the data using Principal Component Analysis (PCA).
                    Focus on transforming the data to a lower-dimensional space while retaining most of the variance.
                    """;
            case LINEAR_DISCRIMINANT_ANALYSIS -> """
                    Your purpose is to reduce the dimensionality of the data using Linear Discriminant Analysis (LDA).
                    Focus on maximizing the separability between different classes.
                    """;
            case FACTOR_ANALYSIS -> """
                    Your purpose is to reduce the dimensionality of the data using Factor Analysis.
                    Focus on modeling the data using latent variables.
                    """;
            case T_SNE -> """
                    Your purpose is to reduce the dimensionality of the data using t-Distributed Stochastic Neighbor Embedding (t-SNE).
                    Focus on visualizing high-dimensional data by reducing it to two or three dimensions.
                    """;
            case UMAP -> """
                    Your purpose is to reduce the dimensionality of the data using Uniform Manifold Approximation and Projection (UMAP).
                    Focus on preserving the local and global structure of the data.
                    """;
        };
    }
}
