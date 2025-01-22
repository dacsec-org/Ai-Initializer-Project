package org.dacss.projectinitai.downloaders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>{@link DownloadersDetails}</h1>
 * <p>
 *     This class is used to store the details of the LLM downloads.
 *     We will convert it into an entity later to query the database,
 *     rather than downloading the entire list of LLMs every time.
 * </p>
 */
public class DownloadersDetails {
    private String name;
    private String description;
    private String type;
    private String sizes;
    private String pulls;
    private String tags;
    private String updated;
    private boolean isInstalled;
    private String dateInstalled;
    private String availableSizes;

    public DownloadersDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(String availableSizes) {
        this.availableSizes = availableSizes;
    }

    public String getDateInstalled() {
        return dateInstalled;
    }

    public void setDateInstalled() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateInstalled = LocalDate.now().format(formatter);
    }

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPulls() {
        return pulls;
    }

    public void setPulls(String pulls) {
        this.pulls = pulls;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
