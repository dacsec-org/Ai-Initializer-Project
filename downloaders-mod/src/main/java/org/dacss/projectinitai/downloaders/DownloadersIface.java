package org.dacss.projectinitai.downloaders;
/**/

/**
 * <h1>{@link DownloadersIface}</h1>
 */
@FunctionalInterface
public interface DownloadersIface {
    /**
     * <h2>{@link #download(String, String, String)}</h2>
     * Downloads the file from the given URL.
     *
     * @param action The action to perform.
     * @param url The URL to download the file from.
     * @param filePath The path to save the downloaded file.
     */
    void download(String action, String url, String filePath);
}
