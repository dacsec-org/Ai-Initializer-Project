package org.dacss.projectinitai.downloaders;
/**/

/**
 * <h1>{@link DownloadersIface}</h1>
 */
@FunctionalInterface
public interface DownloadersIface {
    /**
     * <h2>{@link #download(String)}</h2>
     * Downloads the file from the given URL.
     *
     * @param action The action to perform.
     */
    void download(String action);
}
