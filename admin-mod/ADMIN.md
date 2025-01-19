# Admin Module

## Universal LLM Client's Adviser/Framework Admin(Qwen2.5-0.5b)

This document outlines the functionalities of a small 0.5 billion parameter construct LLM used 
as a universal LLM client's adviser and framework admin. The admin LLM has access to all tools 
and modules within the framework, ensuring the target LLM always stays in the correct context.

### Functionalities

1. **Advising Other LLMs**:
   - Provides guidance and recommendations to other LLMs within the framework.
   - Example: Suggests optimal configurations for other LLMs based on their usage patterns.

2. **Checksum Handling**:
   - Verifies the integrity of files and data using checksums.
   - Example: Generates and validates checksums for downloaded models to ensure data integrity.

3. **Directory Handling**:
   - Manages directories, including creating, deleting, and organizing directories.
   - Example: Automatically organizes model files into appropriate directories based on their types.

4. **LLM Downloader**:
   - Downloads and manages LLM models from various sources.
   - Example: Downloads the latest version of a specific LLM model and stores it in the designated directory.

5. **Contexts Management**:
   - Manages contexts for different LLMs, ensuring they operate within the correct context.
   - Example: Loads and unloads contexts dynamically based on the current task.

6. **Dynamically load/Unload Other LLMs**:
   - Loads and unloads other LLMs as needed on the most efficient processor (CPU-GPU)
   - Example: Loads a specific LLM for a particular task and unloads it when the task is completed.

7. **Metrics for the Framework**:
   - Collects and reports metrics for the framework, such as performance and usage statistics.
   - Example: Generates reports on the performance of different LLMs within the framework.

8. **Creating New/Merging Multiple LLMs**:
   - Creates new LLMs or merges multiple LLMs into a single model.
   - Example: Combines the capabilities of two LLMs to create a more powerful model.

9. **Pre-Post Processing**:
   - Handles pre-processing and post-processing tasks for data and models.
   - Example: Pre-processes input data before feeding it to an LLM and post-processes the output for better readability.

10. **RAG (Retrieval-Augmented Generation) Functionality**:
    - Enhances LLMs with RAG functionality, improving their ability to generate accurate responses.
    - Example: Integrates a retrieval system to provide relevant context to the LLM before generating a response.

11. **Framework Security**:
    - Enforces security best practices within the framework.
    - Example: Implements authentication and authorization mechanisms to secure access to LLMs and data.

12. **Backend Servers for the Framework**:
    - Manages backend servers that support the framework.
    - Example: Deploys and monitors backend servers to ensure they are running optimally.

13. **Snapshots**:
    - Creates and manages snapshots of LLMs and their states.
    - Example: Creates a snapshot of an LLM before making significant changes, allowing for easy rollback if needed.

14. **Data Types Management**:
    - Handles various data types used within the framework.
    - Example: Converts data between different formats as required by different LLMs.

## Admin Management System

### Overview

This document provides an overview of the admin management system implemented using Java and a frontend in TypeScript/React. The system allows users to manage various aspects of the framework, including advising other LLMs, checksum handling, directory handling, and more.

### Main Purpose

The admin management system is designed to provide an easy-to-use interface for managing the framework's functionalities. The system should allow users to perform the following actions:
- Advise other LLMs.
- Handle checksums.
- Manage directories.
- Download and manage LLM models.
- Manage contexts for different LLMs.
- Load and unload other LLMs.
- Collect and report metrics.
- Create new or merge multiple LLMs.
- Handle pre-processing and post-processing tasks.
- Enhance LLMs with RAG functionality.
- Enforce security best practices.
- Manage backend servers.
- Create and manage snapshots.
- Handle various data types.

### Backend (Java)

#### `AdminService`
- **Purpose**: Handles admin-related actions and is exposed to the frontend via Vaadin.
- **Methods**:
  - `handleAdminAction(String action, String source, String destination)`: Delegates the action to `AdminHandler`.

#### `AdminHandler`
- **Purpose**: Encapsulates the logic for handling admin tasks.
- **Dependencies**:
  - `AdviserService(advisers-mod)`
  - `ChecksumsService(checksums-mod)`
  - `ContextsService(contexts-mod)`
  - `DirFileService(directories-mod)`
  - `DownloaderService(downloaders-mod)`
  - `ModelLoadUnloadService(loaders-mod)`
  - `MetricsService(metrics-mod)`
  - `ModelsService(models-mod)`
  - `ProcessorsService(processors-mod)`
  - `RAGService(rags-mod)`
  - `SecurityService(security-mod)`
  - `ServersService(servers-mod)`
  - `SnapshotsService(snapshots-mod)`
  - `TypesService(types-mod)`
- **Methods**:
  - `adviseLLM(String llmId)`: Provides advice to the specified LLM.
  - `handleChecksum(String filePath)`: Handles checksum operations.
  - `manageDirectory(String directoryPath)`: Manages directories.
  - `downloadLLM(String modelUrl)`: Downloads an LLM model.
  - `manageContext(String contextId)`: Manages contexts.
  - `loadLLM(String llmId)`: Loads an LLM.
  - `collectMetrics()`: Collects metrics.
  - `createOrMergeLLM(String llmId)`: Creates or merges LLMs.
  - `preProcessData(String data)`: Pre-processes data.
  - `postProcessData(String data)`: Post-processes data.
  - `enhanceWithRAG(String llmId)`: Enhances LLM with RAG functionality.
  - `enforceSecurity()`: Enforces security best practices.
  - `manageBackendServer(String serverId)`: Manages backend servers.
  - `createSnapshot(String source, String destination)`: Creates a snapshot.
  - `handleDataType(String dataType)`: Handles various data types.

#### `AdminConf`
- **Purpose**: Spring configuration class that defines beans for the admin-related classes.
- **Beans**:
  - `AdminHandler`
  - `AdviserService`
  - `ChecksumsService`
  - `DirFileService`
  - `DownloaderService`
  - `ContextsService`
  - `ModelLoadUnloadService`
  - `MetricsService`
  - `ModelsService`
  - `ProcessorsService`
  - `RAGService`
  - `SecurityService`
  - `ServersService`
  - `SnapshotsService`
  - `TypesService`

### Frontend (TypeScript/React)

#### `admin-view.tsx`
- **Purpose**: React component that provides a user interface for managing admin tasks.
- **Methods**:
  - `adviseLLM()`: Advises an LLM.
  - `handleChecksum()`: Handles checksum operations.
  - `manageDirectory()`: Manages directories.
  - `downloadLLM()`: Downloads an LLM model.
  - `manageContext()`: Manages contexts.
  - `loadLLM()`: Loads an LLM.
  - `collectMetrics()`: Collects metrics.
  - `createOrMergeLLM()`: Creates or merges LLMs.
  - `preProcessData()`: Pre-processes data.
  - `postProcessData()`: Post-processes data.
  - `enhanceWithRAG()`: Enhances LLM with RAG functionality.
  - `enforceSecurity()`: Enforces security best practices.
  - `manageBackendServer()`: Manages backend servers.
  - `createSnapshot()`: Creates a snapshot.
  - `handleDataType()`: Handles various data types.
- **UI Elements**:
  - Buttons for performing admin tasks.
  - Dialogs for confirming actions.

### Interaction Flow

1. **User Interaction**:
   - The user interacts with the React component (`admin-view.tsx`), clicking buttons to perform admin tasks.

2. **Frontend to Backend**:
   - The React component calls methods on `AdminService` to perform the requested actions.

3. **Backend Processing**:
   - `AdminService` delegates the action to `AdminHandler`, which in turn
     uses the appropriate class (`AdviserService`, `ChecksumsService`,
     `DirFileService`, etc.) to perform the task.

4. **Result Handling**:
   - The results (e.g., metrics, snapshots) are sent back to the frontend and displayed to the user.

This setup ensures a clear separation of concerns, with each class performing a specific role and the frontend communicating with the backend through well-defined service methods.

### Technologies Used

- **Languages**: Java, TypeScript, JavaScript
- **Frameworks**: Spring Boot, React
- **Build Tools**: Maven, npm
