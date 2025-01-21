package org.dacss.projectinitai.reductions;

/**
 * <h1>{@link ReductionsIface}</h1>
 */
@FunctionalInterface
public interface ReductionsIface {
    /**
     * <h2>{@link #reduceDimensions()}</h2>
     * Perform dimensionality reduction on the data.
     */
    void reduceDimensions();
}
