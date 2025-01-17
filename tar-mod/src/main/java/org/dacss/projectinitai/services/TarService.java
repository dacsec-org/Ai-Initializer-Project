package org.dacss.projectinitai.services;
/**/
import org.dacss.projectinitai.utillities.TarCompressorUtil;
import org.dacss.projectinitai.utillities.TarExtractorUtil;
import org.dacss.projectinitai.utillities.TarDestroyUtil;
/**/
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * <h1>{@link TarService}</h1>
 * Service class to create, extract and delete tar files callable from the browser
 * via a Vaadin hilla *.tsx file.
 */
@Service
@BrowserCallable
public class TarService {

    /**
     * {@link #createTar(File, File)}
     * Method to create a tar file from a source directory.
     *
     * @param sourceDir
     * @param tarFile
     * @throws IOException
     */
    public void createTar(File sourceDir, File tarFile) throws IOException {
        TarCompressorUtil.createTarFile(sourceDir, tarFile);
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
        TarExtractorUtil.extractTarFile(tarFile, destDir);
    }

    /**
     * {@link #deleteTar(File)}
     * Method to delete a tar file.
     *
     * @param tarFile
     * @throws IOException
     */
    public void deleteTar(File tarFile) throws IOException {
        if (!tarFile.exists()) {
            throw new IOException(STR."Tar file does not exist: \{tarFile.getAbsolutePath()}");
        }
        if (!tarFile.canWrite()) {
            throw new IOException(STR."No write permission for tar file: \{tarFile.getAbsolutePath()}");
        }
        if (!tarFile.delete()) {
            throw new IOException(STR."Failed to delete tar file: \{tarFile.getAbsolutePath()}");
        }
    }

    /**
     * {@link #extractAndDestroyTar(File, File)}
     * Method to extract a tar file to a destination directory and destroy the tar file.
     *
     * @param tarFile
     * @param destDir
     * @return
     * @throws IOException
     */
    public String extractAndDestroyTar(File tarFile, File destDir) throws IOException {
        extractTar(tarFile, destDir);
        return TarDestroyUtil.destroyTarFile(tarFile, destDir);
    }
}
