package org.dacss.projectinitai.servers;

/**
 * <h1>{@link ServersIface}</h1>
 */
@FunctionalInterface
public interface ServersIface {
    /**
     * <h2>{@link #manageServer()}</h2>
     * Perform server management operations.
     */
    void manageServer(String operation);
}
