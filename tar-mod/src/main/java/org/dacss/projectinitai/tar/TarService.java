package org.dacss.projectinitai.tar;

import org.dacss.projectinitai.tar.utillities.TarCompressorUtil;
import org.dacss.projectinitai.tar.utillities.TarExtractorUtil;
import org.dacss.projectinitai.tar.utillities.TarDestroyUtil;
import com.vaadin.hilla.BrowserCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TarService implements TarIface {

    private static final Logger log = LoggerFactory.getLogger(TarService.class);

    /**
     * {@link #TarService()} 0-arg constructor.
     */
    public TarService() {}

    /**
     * {@link #processTar(String, File, File, File)} method to create, extract, delete or extract and destroy tar files.
     *
     * @param action    The action to perform on the tar file.
     * @param sourceDir The source directory to create the tar file from.
     * @param tarFile   The tar file to create, extract or delete.
     * @param destDir   The destination directory to extract the tar file to.
     */
    @Override
    public void processTar(String action, File sourceDir, File tarFile, File destDir) {
        try {
            switch (action.toLowerCase()) {
                case "create":
                    TarCompressorUtil.createTarFile(sourceDir, tarFile);
                    break;
                case "extract":
                    TarExtractorUtil.extractTarFile(tarFile, destDir);
                    break;
                case "delete":
                    if (!tarFile.delete()) {
                        throw new IOException(STR."Failed to delete tar file: \{tarFile.getAbsolutePath()}");
                    }
                    break;
                case "extract_and_destroy":
                    TarExtractorUtil.extractTarFile(tarFile, destDir);
                    TarDestroyUtil.destroyTarFile(tarFile, destDir);
                    break;
                default:
                    throw new IllegalArgumentException(STR."Unknown action: \{action}");
            }
        } catch (Exception tarExc) {
            log.error("Error handling tar operation: {}", action, tarExc);
        }
    }
}
