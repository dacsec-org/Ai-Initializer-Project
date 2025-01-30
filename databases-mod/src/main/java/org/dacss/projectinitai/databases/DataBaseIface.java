package org.dacss.projectinitai.databases;
/**/

/**
 * <h1>{@link DataBaseIface}</h1>
 */
@FunctionalInterface
public interface DataBaseIface {
    /**
     * <h2>{@link #performDatabaseAction(String)}</h2>
     */
    void performDatabaseAction(String action);
}
