package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.clustering.ClusteringIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ClusteringService}</h1>
 * Backend hilla endpoint service for clustering data.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class ClusteringService implements ClusteringIface {


    private static final Logger log = LoggerFactory.getLogger(ClusteringService.class);

    /**
     * <h2>{@link #ClusteringService()}</h2>
     */
    public ClusteringService() {
    }

    /**
     * <h2>{@link #performClustering()}</h2>
     */
    @Override
    public void performClustering() {

    }
}

//    /**
//     * <h2>{@link #handleClusteringAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be clustered.
//     * @return The result of the action.
//     */
//    public Object handleClusteringAction(String action, String data) {
//        return switch (ClusteringContexts.valueOf(action.toUpperCase())) {
//            case KMEANS -> handler.clusterWithKMeans(data);
//            case DBSCAN -> handler.clusterWithDBSCAN(data);
//            case HIERARCHICAL -> handler.clusterWithHierarchical(data);
//            case GAUSSIAN_MIXTURE -> handler.clusterWithGaussianMixture(data);
//            case SPECTRAL -> handler.clusterWithSpectral(data);
//        };
//    }
