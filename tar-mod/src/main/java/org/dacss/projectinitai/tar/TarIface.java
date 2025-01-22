package org.dacss.projectinitai.tar;
/**/

import java.io.File;
import java.io.IOException;

/**
 * <h1>{@link TarIface}</h1>
 */
@FunctionalInterface
public interface TarIface {
    /**
     * <h2>{@link TarIface#processTar(String, File, File, File)}</h2>
     */
    void processTar(String action, File sourceDir, File tarFile, File destDir) throws IOException;
}
