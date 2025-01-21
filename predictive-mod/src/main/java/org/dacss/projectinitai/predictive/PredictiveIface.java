package org.dacss.projectinitai.predictive;

/**
 * <h1>{@link PredictiveIface}</h1>
 */
@FunctionalInterface
public interface PredictiveIface {
    /**
     * <h2>{@link #predict()}</h2>
     * Perform predictive analytics on the data.
     */
    void predict();
}
