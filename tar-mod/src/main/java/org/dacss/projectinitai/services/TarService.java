package org.dacss.projectinitai.services;

import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.utillities.TarCompressorUtil;
import org.dacss.projectinitai.utillities.TarExtractorUtil;
import org.dacss.projectinitai.utillities.TarDestroyUtil;
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

    public void createTar(File sourceDir, File tarFile) throws IOException {
        TarCompressorUtil.createTarFile(sourceDir, tarFile);
    }

    public void extractTar(File tarFile, File destDir) throws IOException {
        TarExtractorUtil.extractTarFile(tarFile, destDir);
    }

    public void deleteTar(File tarFile) throws IOException {
        if (!tarFile.exists()) {
            throw new IOException("Tar file does not exist: " + tarFile.getAbsolutePath());
        }
        if (!tarFile.canWrite()) {
            throw new IOException("No write permission for tar file: " + tarFile.getAbsolutePath());
        }
        if (!tarFile.delete()) {
            throw new IOException("Failed to delete tar file: " + tarFile.getAbsolutePath());
        }
    }

    public String extractAndDestroyTar(File tarFile, File destDir) throws IOException {
        extractTar(tarFile, destDir);
        return TarDestroyUtil.destroyTarFile(tarFile, destDir);
    }
}
