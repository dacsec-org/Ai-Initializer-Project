package org.dacss.projectinitai.downloaders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * <h1>{@link ModelInfo}</h1>
 * Represents the information of a model.
 */
public class ModelInfo {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("id")
    private String modelId;

    @JsonProperty("likes")
    private int likes;

    @JsonProperty("trendingScore")
    private int trendingScore;

    @JsonProperty("private")
    private boolean isPrivate;

    @JsonProperty("downloads")
    private int downloads;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("pipeline_tag")
    private String pipelineTag;

    @JsonProperty("library_name")
    private String libraryName;

    @JsonProperty("createdAt")
    private String createdAt;

    // Getters and setters

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getModelId() { return modelId; }

    public void setModelId(String modelId) { this.modelId = modelId; }

    public int getLikes() { return likes; }

    public void setLikes(int likes) { this.likes = likes; }

    public int getTrendingScore() { return trendingScore; }

    public void setTrendingScore(int trendingScore) { this.trendingScore = trendingScore; }

    public boolean isPrivate() { return isPrivate; }

    public void setPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }

    public int getDownloads() { return downloads; }

    public void setDownloads(int downloads) { this.downloads = downloads; }

    public List<String> getTags() { return tags; }

    public void setTags(List<String> tags) { this.tags = tags; }

    public String getPipelineTag() { return pipelineTag; }

    public void setPipelineTag(String pipelineTag) { this.pipelineTag = pipelineTag; }

    public String getLibraryName() { return libraryName; }

    public void setLibraryName(String libraryName) { this.libraryName = libraryName; }

    public String getCreatedAt() { return createdAt; }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
