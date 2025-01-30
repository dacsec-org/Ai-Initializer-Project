package org.dacss.projectinitai.databases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * <h1>{@link LLMDetails}</h1>
 * <p>
 *     This class is used to store the details of the LLM downloads.
 *     We will convert it into an entity later to query the database,
 *     rather than downloading the entire list of LLMs every time.
 * </p>
 */
@Entity
public class LLMDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String modelId;
    private int likes;
    private int trendingScore;
    private boolean isPrivate;
    private int downloads;
    private String pipelineTag;
    private String libraryName;
    private String createdAt;

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

    public String getPipelineTag() { return pipelineTag; }

    public void setPipelineTag(String pipelineTag) { this.pipelineTag = pipelineTag; }

    public String getLibraryName() { return libraryName; }

    public void setLibraryName(String libraryName) { this.libraryName = libraryName; }

    public String getCreatedAt() { return createdAt; }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
