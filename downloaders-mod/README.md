# Downloaders Module

## Module for Handling Downloads

### `DownloadersService`

Backend service for handling various download actions related to Large Language Models (LLMs).

**Module Dependencies:**
- `directories-mod` (`DirFileHandler`)
- `security-mod` (`CredentialService`)

**Methods:**
- `download(DownloadAction action, String llmName)`: Handles different download actions based on the provided `DownloadAction` enum and LLM name. Returns a `Flux<Object>`.

### `DownloadAction`

Enum representing various download actions for LLMs.

**Actions:**
- `API_TOKEN`: Action to download an API token.
- `DOWNLOAD_LLM_JSON`: Action to download LLM list JSON.
- `DOWNLOAD_LLM_MODEL`: Action to download the LLM model files.
- `SEARCH`: Action to query Hugging Face for LLMs.

### `DownloadersIface`

Functional interface for download actions.

**Methods:**
- `download(DownloadAction action, String llmName)`: Abstract method to be implemented for handling download actions.

### `FileDownloadInfo`

Class representing information about a file to be downloaded.

**Fields:**
- `fileName`: The name of the file.
- `fileUrl`: The URL from which the file can be downloaded.

**Methods:**
- Getters and setters for all fields.

### `LLMDownloader`

Class for downloading LLM model files.

**Methods:**
- `downloadLLM(DownloadAction action, String llmName)`: Downloads LLM model files based on the provided action and LLM name. Returns a `Flux<Object>`.

### `LLMLibraryUtil`

Utility class for downloading LLM JSON files.

**Methods:**
- `downloadLLMJsonFile()`: Downloads the LLM JSON file from a specified URL. Returns a `Flux<Object>`.

### `ModelInfo`

Class representing the information of a model.

**Fields:**
- `id`: The unique identifier of the model.
- `modelId`: The model ID.
- `likes`: The number of likes.
- `trendingScore`: The trending score.
- `isPrivate`: Indicates if the model is private.
- `downloads`: The number of downloads.
- `tags`: The tags associated with the model.
- `pipelineTag`: The pipeline tag.
- `libraryName`: The library name.
- `createdAt`: The creation date.

**Methods:**
- Getters and setters for all fields.

### `SearchModels`

Class for searching models on Hugging Face.

**Methods:**
- `searchModels(DownloadAction action, String query)`: Searches for models based on the provided action and query. Returns a `Flux<JsonNode>`.