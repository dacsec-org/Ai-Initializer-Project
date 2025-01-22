package org.dacss.projectinitai.directories;

/**
 * <h1>{@link DirectoriesIface}</h1>
 */
@FunctionalInterface
public interface DirectoriesIface {
    /**
     * <h2>{@link #processDirFileAction(String, String, String)}</h2>
     */
    void processDirFileAction(String action, String path, String fileName);
}
