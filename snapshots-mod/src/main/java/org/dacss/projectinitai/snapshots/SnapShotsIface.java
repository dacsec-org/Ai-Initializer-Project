package org.dacss.projectinitai.snapshots;

/**
 * <h1>{@link SnapShotsIface}</h1>
 */
@FunctionalInterface
public interface SnapShotsIface {
    /**
     * <h2>{@link #manageSnapshots(String action, String source, String destination)}</h2>
     * Perform snapshot management operations.
     */
    void manageSnapshots(String action, String source, String destination);
}
