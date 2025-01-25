package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.tar.TarIface;
import org.dacss.projectinitai.tar.utillities.TarCompressorUtil;
import org.dacss.projectinitai.tar.utillities.TarExtractorUtil;
import org.dacss.projectinitai.tar.utillities.TarDestroyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * <h1>{@link TarService}</h1>
 * Service class to create, extract and delete tar files callable from the browser
 * via a Vaadin hilla *.tsx file.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class TarService implements TarIface {

    private static final Logger log = LoggerFactory.getLogger(TarService.class);

    /**
     * <h2>{@link #TarService()}</h2> 0-arg constructor.
     *
     */
    public TarService() {}

    /**
     * <h2>{@link #processTar(String, File, File, File)}</h2>
     * Implementation of the Functional Interface {@link TarIface} method.
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
                        throw new IOException(MessageFormat.format("Failed to delete tar file: {0}", tarFile));
                    }
                    break;
                case "extract_and_destroy":
                    TarExtractorUtil.extractTarFile(tarFile, destDir);
                    TarDestroyUtil.destroyTarFile(tarFile, destDir);
                    break;
                default:
                    throw new IllegalArgumentException (MessageFormat.format("Invalid action: {0}", action));
            }
        } catch (Exception tarExc) {
            log.error("Error handling tar operation: {}", action, tarExc);
        }
    }
}
