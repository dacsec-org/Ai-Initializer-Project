package org.dacss.projectinitai.checksums;
/**/

/**
 * <h1>{@link ChecksumsIface}</h1>
 */
@FunctionalInterface
public interface ChecksumsIface {
    /**
     * <h2>{@link ChecksumsIface#calculateChecksum(String, String, String)}</h2>
     * Handles the checksum operations.
     */
    void calculateChecksum(String action, String filePath, String expectedChecksum);
}
