package org.dacss.projectinitai.downloaders;
/**/

import com.vaadin.hilla.BrowserCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.dacss.projectinitai.checksums.utillities.ChecksumGeneratorUtil.generateSHA512;
import static org.dacss.projectinitai.directories.utilities.CreateDirFileUtil.createDirectory;
import static org.dacss.projectinitai.security.utilities.SecurityApiTokenUtil.getApiToken;

/**
 * <h1>{@link DownloadersService}</h1>
 * <p>
 * Backend service for downloading models from the Hugging Face model hub.
 * </p>
 */
@Service
@BrowserCallable
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
                    DownloadersScraperUtil.scrapeLLMLinks(url);
                    break;
                case "create_directory":
                    createDirectory(url);
                    break;
                //todo: add the checksum generator to the switch statement
                case "generate_checksum":
                    generateSHA512(filePath);
                    break;
                default:
                    throw new IllegalArgumentException(STR."Unsupported operation: \{action}");
            }
        } catch (Exception downloadExc) {
            log.error("Error downloading file: {}", url, downloadExc);
        }
    }
}
