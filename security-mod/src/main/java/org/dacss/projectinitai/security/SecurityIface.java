package org.dacss.projectinitai.security;

import java.io.IOException;

/**
 * <h1>{@link SecurityIface}</h1>
 */
@FunctionalInterface
public interface SecurityIface {
    /**
     * <h2>{@link #secure()}</h2>
     * Perform security operations on the data.
     */
    void secure() throws IOException;
}
