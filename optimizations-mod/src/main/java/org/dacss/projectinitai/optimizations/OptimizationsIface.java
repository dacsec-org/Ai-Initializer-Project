package org.dacss.projectinitai.optimizations;

/**
 * <h1>{@link OptimizationsIface}</h1>
 */
@FunctionalInterface
public interface OptimizationsIface {
    /**
     * <h2>{@link #optimize(String, String)}</h2>
     * Perform optimization on the data.
     *
     * @param action The optimization action to perform.
     * @param data The data to optimize.
     */
    void optimize(String action, String data);
}
