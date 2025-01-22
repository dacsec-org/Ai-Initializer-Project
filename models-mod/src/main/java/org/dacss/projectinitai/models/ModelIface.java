package org.dacss.projectinitai.models;

import java.io.IOException;

/**
 * <h1>{@link ModelIface}</h1>
 */
@FunctionalInterface
public interface ModelIface {
    /**
     * <h2>{@link #processModel()}</h2>
     * Perform model operations.
     */
    void processModel(String action, String modelPath1, String modelPath2) throws IOException;
}
