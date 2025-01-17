package org.dacss.projectinitai.handlers;
/**/
import org.dacss.projectinitai.services.TarService;
/**/
import java.io.File;
import java.io.IOException;

/**
 * <h1>{@link TarHandler}</h1>
 * Handler class to create, extract and delete tar files.
 */
public class TarHandler {

    private final TarService tarService;

    /**
     * {@link #TarHandler()} 0-arg constructor to instantiate the {@link TarService}.
     */
    public TarHandler() {
        this.tarService = new TarService();
    }

    /**
     * {@link #createTar(File, File)}
     * Method to create a tar file from a source directory.
     *
     * @param sourceDir
     * @param tarFile
     * @throws IOException
     */
    public void createTar(File sourceDir, File tarFile) throws IOException {
        tarService.createTar(sourceDir, tarFile);
    }

    /**
     * {@link #extractTar(File, File)}
     * Method to extract a tar file to a destination directory.
     *
     * @param tarFile
     * @param destDir
     * @throws IOException
     */
    public void extractTar(File tarFile, File destDir) throws IOException {
        tarService.extractTar(tarFile, destDir);
    }

    /**
     * {@link #deleteTar(File)}
     * Method to delete a tar file.
     *
     * @param tarFile
     * @throws IOException
     */
    public void deleteTar(File tarFile) throws IOException {
        tarService.deleteTar(tarFile);
    }

    /**
     * {@link #extractAndDestroyTar(File, File)}
     * Method to extract and destroy a tar file.
     *
     * @param tarFile
     * @param destDir
     * @throws IOException
     */
    public void extractAndDestroyTar(File tarFile, File destDir) throws IOException {
        tarService.extractAndDestroyTar(tarFile, destDir);
    }
}
