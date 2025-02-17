# this readme is out-dated
## `DOWNLOADERS.md`

## Module for Handling Downloads

### `HuggingFaceDownloaderService`

Backend service for downloading models from the Hugging Face model hub.

**Module Dependencies:**
- `directories-mod` (`DirFileHandler`)
- `security-mod` (`CredentialService`)

**Methods:**
- `getApiToken()`: Returns the API token.
- `downloadModel(String modelId)`: Downloads a model by its ID. Returns `true` if the model was downloaded successfully.

### `LLMLinkScraper`

Utility class for scraping links of Language Model Libraries (LLMs).

**Methods:**
- `scrapeLLMLinks(String url)`: Scrapes LLM links from the given URL. Returns a list of `LLMS` objects.
- `scrapeName(String input)`: Extracts the name from the input string.
- `scrapeDescription(String input)`: Extracts the description from the input string.
- `scrapeType(String input)`: Extracts the type from the input string.
- `scrapeAvailableSizes(String input)`: Extracts the available sizes from the input string.
- `scrapePulls(String input)`: Extracts the number of pulls from the input string.
- `scrapeTags(String input)`: Extracts the tags from the input string.
- `scrapeUpdated(String input)`: Extracts the updated date from the input string.

### `LLMS`

Represents a Language Model Library.

**Fields:**
- `name`: The name of the LLM.
- `description`: The description of the LLM.
- `type`: The type of the LLM.
- `sizes`: The sizes of the LLM.
- `pulls`: The number of pulls of the LLM.
- `tags`: The tags associated with the LLM.
- `updated`: The last updated date of the LLM.
- `isInstalled`: Indicates if the LLM is installed.
- `dateInstalled`: The date the LLM was installed.
- `availableSizes`: The available sizes of the LLM.

**Methods:**
- Getters and setters for all fields.
- `setDateInstalled()`: Sets the installation date to the current date.
