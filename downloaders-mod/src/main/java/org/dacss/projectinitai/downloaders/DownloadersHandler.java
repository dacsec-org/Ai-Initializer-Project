package org.dacss.projectinitai.downloaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link DownloadersHandler}</h1>
 * This class is used to handle the downloaders actions.
 */
@Component
public class DownloadersHandler implements DownloadersIface {

    private final DownloadersService downloadersService;

    /**
     * <h2>{@link #DownloadersHandler(DownloadersService)}<h2>
     * @param downloadersService The service to download the model.
     */
    @Autowired
    public DownloadersHandler(DownloadersService downloadersService) {
        this.downloadersService = downloadersService;
    }

    /**
     * <h2>{@link #download(String)}</h2>
     *     Downloads the file from the given URL.
     * @param url The URL to download the file from.
     * @return The path to the downloaded file.
     */
    @Override
    public String download(String url) {
        boolean success = downloadersService.downloadModel(url);
        return success ? "Download successful" : "Download failed";
    }
}
