package org.dacss.projectinitai.clustering;

/**
 * <h1>{@link ClusteringContexts}</h1>
 * Context messages for different clustering models.
 */
public enum ClusteringContexts {
    KMEANS,
    DBSCAN,
    HIERARCHICAL,
    GAUSSIAN_MIXTURE,
    SPECTRAL;

    public String getContextMessage() {
        return switch (this) {
            case KMEANS -> """
                    Your purpose is to perform clustering using the K-Means algorithm.
                    Focus on partitioning the data into k clusters.
                    """;
            case DBSCAN -> """
                    Your purpose is to perform clustering using the DBSCAN algorithm.
                    Emphasize finding clusters of varying shapes and handling noise.
                    """;
            case HIERARCHICAL -> """
                    Your purpose is to perform clustering using hierarchical clustering.
                    Focus on creating a hierarchy of clusters.
                    """;
            case GAUSSIAN_MIXTURE -> """
                    Your purpose is to perform clustering using Gaussian Mixture Models.
                    Emphasize modeling the data as a mixture of multiple Gaussian distributions.
                    """;
            case SPECTRAL -> """
                    Your purpose is to perform clustering using spectral clustering.
                    Focus on using the eigenvalues of the similarity matrix to perform dimensionality reduction before clustering.
                    """;
        };
    }
}
