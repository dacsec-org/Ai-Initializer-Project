package org.dacss.projectinitai.downloaders;

/**
 * <h1>{@link DownloadAction}</h1>
 * Enum representing various download actions for LLMs (Large Language Models).
 * This enum defines the different types of download actions that can be performed.
 */
public enum DownloadAction {
    /**
     * Action to download an API token.
     */
    API_TOKEN,

    /**
     * Action to download LLM JSON configuration files.
     */
    DOWNLOAD_LLM_JSON,

    /**
     * Action to download the LLM model files.
     */
    DOWNLOAD_LLM_MODEL,

    /**
     * Action to query huggingface for llm's.
     */
    SEARCH
}
