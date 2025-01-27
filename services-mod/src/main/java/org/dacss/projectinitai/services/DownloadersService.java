package org.dacss.projectinitai.services;
/**/

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.downloaders.DownloadersIface;
import org.dacss.projectinitai.downloaders.DownloadersScraperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import static org.dacss.projectinitai.checksums.utillities.ChecksumGeneratorUtil.generateSHA512;
import static org.dacss.projectinitai.directories.utilities.CreateDirFileUtil.createDirectory;
import static org.dacss.projectinitai.downloaders.DownloadersScraperUtil.scrapeLLMLinks;
import static org.dacss.projectinitai.security.utilities.SecurityApiTokenUtil.getApiToken;

/**
 * <h1>{@link DownloadersService}</h1>
 * <p>
 * Backend service for downloading models from the Hugging Face model hub.
 * </p>
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class DownloadersService implements DownloadersIface {

    private static final Logger log = LoggerFactory.getLogger(DownloadersService.class);

    /**
     * <h2>{@link #DownloadersService()}</h2>
     * <p>
     * 0-parameter constructor.
     * </p>
     */
    public DownloadersService() {}

    /**
     * Downloads the file from the given URL.
     *
     * @param action   The action to perform.
     * @param url      The URL to download the file from.
     * @param filePath The path to the file.
     */
    @Override
    public void download(String action, String url, String filePath) {
        try {
            switch (action.toLowerCase()) {
                case "api_token":
                    getApiToken();
                    break;
                case "download":
                    scrapeLLMLinks(url);
                    break;
                case "create_directory":
                    createDirectory(url);
                    break;
                case "generate_checksum":
                    generateSHA512(filePath);
                    break;
                default:
                    throw new IllegalArgumentException(MessageFormat.format("Invalid action: {0}", action));
            }
        } catch (Exception downloadExc) {
            log.error("Error downloading file: {}", url, downloadExc);
        }
    }
}
