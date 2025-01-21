package org.dacss.projectinitai.tar;
/**/
import org.dacss.projectinitai.tar.utillities.TarCompressorUtil;
import org.dacss.projectinitai.tar.utillities.TarExtractorUtil;
import org.dacss.projectinitai.tar.utillities.TarDestroyUtil;
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

    private TarHandler handler;

    /**
     * {@link #TarService()} 0-arg constructor to instantiate the {@link TarHandler}.
     */
    public TarService() {
        this.handler = new TarHandler();
    }

    public Object handleTarAction(String action, File sourceDir, File tarFile, File destDir) {
        return switch (action.toLowerCase()) {
            case "create" -> handler.createTar(sourceDir, tarFile);
            case "extract" -> handler.extractTar(tarFile, destDir);
            case "delete" -> handler.deleteTar(tarFile);
            default ->
                    throw new IllegalArgumentException(STR."Unknown action: \{action}");
        };
    }
}

