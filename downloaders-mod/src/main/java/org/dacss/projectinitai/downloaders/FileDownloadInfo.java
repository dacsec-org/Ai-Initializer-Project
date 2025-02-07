package org.dacss.projectinitai.downloaders;

/**
 * <h1>{@link FileDownloadInfo}</h1>
 * Class representing information about a file to be downloaded.
 * This class contains the file name and the URL from which the file can be downloaded.
 */
public class FileDownloadInfo {
    private String fileName;
    private String fileUrl;

    /**
     * <h3>{@link #FileDownloadInfo(String, String)}</h3>
     *
     * @param fileName the name of the file
     * @param fileUrl the URL from which the file can be downloaded
     */
    public FileDownloadInfo(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    /**
     * <h3>{@link #getFileName()}</h3>
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * <h3>{@link #setFileName(String)}</h3>
     *
     * @param fileName the file name to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * <h3>{@link #getFileUrl()}</h3>
     *
     * @return the file URL
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * <h3>{@link #setFileUrl(String)}</h3>
     *
     * @param fileUrl the file URL to set
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
