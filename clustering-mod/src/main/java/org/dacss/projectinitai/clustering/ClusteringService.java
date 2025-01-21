package org.dacss.projectinitai.clustering;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ClusteringService}</h1>
 * Backend hilla endpoint service for clustering data.
 */
@Service
@BrowserCallable
public class ClusteringService {

    private ClusteringHandler handler;

    /**
     * <h2>{@link #ClusteringService()}</h2>
     * 0-arg constructor to instantiate the {@link ClusteringHandler}.
     */
    public ClusteringService() {
        this.handler = new ClusteringHandler();
    }

    /**
     * <h2>{@link #handleClusteringAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be clustered.
     * @return The result of the action.
     */
    public Object handleClusteringAction(String action, String data) {
        return switch (ClusteringContexts.valueOf(action.toUpperCase())) {
            case KMEANS -> handler.clusterWithKMeans(data);
            case DBSCAN -> handler.clusterWithDBSCAN(data);
            case HIERARCHICAL -> handler.clusterWithHierarchical(data);
            case GAUSSIAN_MIXTURE -> handler.clusterWithGaussianMixture(data);
            case SPECTRAL -> handler.clusterWithSpectral(data);
        };
    }
}
