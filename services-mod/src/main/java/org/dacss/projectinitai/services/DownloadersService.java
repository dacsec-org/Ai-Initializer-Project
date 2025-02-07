package org.dacss.projectinitai.services;

import org.dacss.projectinitai.downloaders.*;
import org.dacss.projectinitai.security.SecurityApiTokenUtil;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DownloadersService}</h1>
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class DownloadersService implements DownloadersIface {

    private static final Logger log = LoggerFactory.getLogger(DownloadersService.class);

    public DownloadersService() {}

    @Override
    public Flux<Object> download(DownloadAction action, String llmName) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case API_TOKEN -> SecurityApiTokenUtil.getApiToken();
                case DOWNLOAD_LLM_JSON -> LLMLibraryUtil.downloadLLMJsonFile();
                case DOWNLOAD_LLM_MODEL -> LLMDownloader.downloadLLM(action, llmName);
                case SEARCH -> SearchModels.searchModels(action, llmName);
            };
        } catch (Exception downloadersServiceExc) {
            log.error("{}:", downloadersServiceExc.getMessage(), downloadersServiceExc);
            return Flux.empty();
        } finally {
            log.info("{}: {}", action, llmName);
        }
        assert flux != null;
        return flux;
    }
}
