package org.dacss.projectinitai.services;

import org.dacss.projectinitai.downloaders.DownloadAction;
import org.dacss.projectinitai.downloaders.DownloadersIface;
import org.dacss.projectinitai.downloaders.utilities.LLMDownloaderUtil;
import org.dacss.projectinitai.downloaders.utilities.LLMLibraryUtil;
import org.dacss.projectinitai.security.utilities.SecurityApiTokenUtil;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DownloadersService}</h1>
 * Service class for handling downloading functionality.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class DownloadersService implements DownloadersIface {

    private static final Logger log = LoggerFactory.getLogger(DownloadersService.class);

    public DownloadersService() {}

    @Override
    public void download(String action) {
        try {
            Flux<Object> flux;
            DownloadAction downloadAction = DownloadAction.valueOf(action.toUpperCase());
            switch (downloadAction) {
                case API_TOKEN:
                    log.info("Retrieving API token...");
                    flux = new SecurityApiTokenUtil().getApiToken();
                    break;
                case DOWNLOAD_LLM_JSON:
                    log.info("Downloading LLM JSON file...");
                    flux = new LLMLibraryUtil().downloadLLMJsonFile();
                    break;
                case DOWNLOAD_LLM_MODEL:
                    log.info("Downloading LLM model...");
                    flux = new LLMDownloaderUtil().downloadLLMModel();
                    break;
                default:
                    log.error("Invalid action: {}", action);
                    return;
            }
            assert flux != null;
            flux.subscribe(new CoreSubscriber<>() {
                @Override
                public void onSubscribe(Subscription subscription) { subscription.request(1); }

                @Override
                public void onNext(Object value) { log.info("{}: {}", action, value); }

                @Override
                public void onError(Throwable throwable) { log.error("Error performing action: {}", action, throwable); }

                @Override
                public void onComplete() { log.info("Action completed successfully: {}", action); }
            });
        } catch (Exception downloadersServiceExc) { log.error("Error performing action: {}", action, downloadersServiceExc); }
    }
}
