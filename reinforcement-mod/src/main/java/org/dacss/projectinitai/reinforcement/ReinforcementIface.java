package org.dacss.projectinitai.reinforcement;

/**
 * <h1>{@link ReinforcementIface}</h1>
 */
@FunctionalInterface
public interface ReinforcementIface {
    /**
     * <h2>{@link #learn()}</h2>
     * Perform reinforcement learning on the data.
     */
    void learn();
}
