package org.dacss.projectinitai.metrics;

/**
 * <h1>{@link MetricsIface}</h1>
 */
@FunctionalInterface
public interface MetricsIface {
    /**
     * <h2>{@link MetricsIface#measure(String)}</h2>
     * measure the data.
     */
    void measure(String metricType);
}
