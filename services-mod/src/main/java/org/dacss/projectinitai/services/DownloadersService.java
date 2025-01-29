package org.dacss.projectinitai.services;

import org.dacss.projectinitai.downloaders.DownloadAction;
import org.dacss.projectinitai.downloaders.DownloadersIface;
import org.dacss.projectinitai.downloaders.DownloadersScraperUtil;
import org.dacss.projectinitai.security.utilities.SecurityApiTokenUtil;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class DownloadersService implements DownloadersIface {

    private static final Logger log = LoggerFactory.getLogger(DownloadersService.class);

    public DownloadersService() {}

    @Override
    public void download(String action, String searchQuery, String filePath) {
        try {
            DownloadAction downloadAction = DownloadAction.valueOf(action.toUpperCase());
            switch (downloadAction) {
                case API_TOKEN:
                    SecurityApiTokenUtil.getApiToken();
                    break;
                case DOWNLOAD:
                    DownloadersScraperUtil.downloadLLMJsonFile();
                    break;
                default:
                    log.error("Invalid action: {}", action);
            }
        } catch (Exception downloadersServiceExc) {
            log.error("Error performing action: {}", action, downloadersServiceExc);
        }
    }
}
