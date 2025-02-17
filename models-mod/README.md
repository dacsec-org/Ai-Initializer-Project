## `MODELS.md`

## Purpose

The `models-mod` module is designed to handle operations related to models,
including merging and destroying models. It provides utilities and services to facilitate these operations,
ensuring efficient and effective model management.

### `ModelHandler.java`
- **Location**: `models-mod/src/main/java/org/dacss/projectinitai/models/handlers/ModelHandler.java`
- **Description**: This class acts as a handler for merging and destroying models.
- It uses utility classes to perform the actual operations.

### `MergeDestroyModelService.java`
- **Location**: `models-mod/src/main/java/org/dacss/projectinitai/models/services/MergeDestroyModelService.java`
- **Description**: This service class provides endpoints for merging and destroying models.
- It interacts with the `ModelHandler` to perform the required actions.

### `MergeModelUtil.java`
- **Location**: `models-mod/src/main/java/org/dacss/projectinitai/models/utilities/MergeModelUtil.java`
- **Description**: This utility class provides methods to merge models.
- It includes logic to merge two or more models, assuming they are JSON files.

### `DestroyModelUtil.java`
- **Location**: `models-mod/src/main/java/org/dacss/projectinitai/models/utilities/DestroyModelUtil.java`
- **Description**: This utility class provides methods to delete models.
- It includes logic to check if a model file exists and delete it if it does.

## Usage

The `models-mod` module can be used to manage models by providing paths to the models that need to be merged or destroyed.
The operations are performed through the service endpoints provided by the `MergeDestroyModelService` class.
