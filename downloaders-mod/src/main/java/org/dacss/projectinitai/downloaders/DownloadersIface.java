package org.dacss.projectinitai.downloaders;
/**/

/**
 * <h1>{@link DownloadersIface}</h1>
 */
@FunctionalInterface
public interface DownloadersIface {
    /**
     * Downloads the file from the given URL.
     *
     * @param url The URL to download the file from.
     * @return The path to the downloaded file.
     */
    String download(String url);
}
