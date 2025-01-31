package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.tar.TarActions;
import org.dacss.projectinitai.tar.TarIface;
import org.dacss.projectinitai.tar.utillities.TarCompressorUtil;
import org.dacss.projectinitai.tar.utillities.TarDestroyUtil;
import org.dacss.projectinitai.tar.utillities.TarExtractorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;

/**
 * <h1>{@link TarService}</h1>
 * Service class to create, extract and delete tar files callable from the browser
 * via a Vaadin hilla *.tsx file.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class TarService implements TarIface {

    private static final Logger log = LoggerFactory.getLogger(TarService.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public TarService() {}

    @Override
    public Flux<Object> processTar(TarActions action) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case COMPRESS -> TarCompressorUtil.createTarFile();
                case EXTRACT -> TarExtractorUtil.extractTarFile();
                case DESTROY -> TarDestroyUtil.destroyTarFile();
            };
        } catch (Exception tarExc) {
            log.error(RED + "Error handling tar operation: {}" + RESET, action, tarExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "TarService action completed: {}" + RESET, action);
        }
        assert flux != null;
        return flux;
    }
}
