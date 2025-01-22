# Snapshots Management System

## Overview

This document provides an overview of the snapshot management system implemented using Java and a frontend in 
TypeScript/React. The system allows users to create, list, update, and delete snapshots.

## Main Purpose
The snapshot management system is designed to provide an easy-to-use interface for managing snapshots in a Btrfs filesystem.
Our use is snapshot entire or parts of LLM's that are stored in Btrfs filesystems. The system should allow users to perform the following actions:
- Create a snapshot of a specified LLM.
- List all snapshots in a specified directory.
- Delete a specified snapshot.
- Copy a snapshot to a new location.
- Snapshot specific parts of LLM's t be loaded into RAM rather than the  entire LLM making it possible to load only the parts of the LLM that are 
  needed. This is especially helpful for large LLM's that are too large to Load into RAM in their entirety. For instance, if a coding assistant 
  type LLM with 60b parameters, the entire LLM is 100GB, but only 1GB is needed for a specific language, we simply snapshot the 1GB target 
  language and load it. This way we don't need 60GB of RAM to load the entire LLM.

## Backend (Java)

### `SnapShotService`
- **Purpose**: Handles snapshot-related actions and is exposed to the frontend via Vaadin.
- **Methods**:
  - `handleSnapshotAction(String action, String source, String destination)`: Delegates the action to `SnapShotHandler`.

### `SnapShotHandler`

- **Purpose**: Encapsulates the logic for handling snapshots.
- **Dependencies**:
  - `SnapShotCreator`
  - `SnapShotLister`
  - `SnapShotDestroyer`
  - `SnapShotCloner`
  - `SnapShotCommandRunner`
- **Methods**:
  - `createSnapshot(String source, String destination)`: Creates a snapshot.
  - `listSnapshots(String directory)`: Lists all snapshots.
  - `deleteSnapshot(String snapshotPath)`: Deletes a snapshot.
  - `copySnapshot(String source, String destination)`: Copies a snapshot.

### `SnapShotCreator`

- **Purpose**: Responsible for creating snapshots by copying files from a source to a destination directory.
- **Methods**:
  - `createSnapshot(String source, String destination)`: Creates a snapshot.
  - `createSnapshotDirectory(Path destinationPath)`: Creates the destination directory if it does not exist.
  - `copyFiles(Path sourcePath, Path destinationPath)`: Copies files from the source to the destination.

### `SnapShotLister`

- **Purpose**: Lists all snapshot directories within a specified directory.
- **Methods**:
  - `listSnapshots(String directory)`: Lists all snapshots.
  - `listSnapshotDirectories(Path dirPath)`: Lists directories in the specified path.

### `SnapShotDestroyer`

- **Purpose**: Deletes a specified snapshot directory and its contents.
- **Methods**:
  - `deleteSnapshot(String snapshotPath)`: Deletes a snapshot.
  - `deleteDirectory(Path path)`: Deletes the directory and its contents.

### `SnapShotCloner`

- **Purpose**: Copies an existing snapshot to a new location.
- **Methods**:
  - `copySnapshot(String source, String destination)`: Copies a snapshot.
  - `copyFiles(Path sourcePath, Path destinationPath)`: Copies files from the source to the destination.

### `SnapShotCommandRunner`

- **Purpose**: Executes Btrfs subvolume commands for snapshot operations.
- **Methods**:
  - `executeCommand(String subcommand, String... args)`: Executes the specified Btrfs command.

### `SnapshotsConf`

- **Purpose**: Spring configuration class that defines beans for the snapshot-related classes.
- **Beans**:
  - `SnapShotHandler`
  - `SnapShotCloner`
  - `SnapShotDestroyer`
  - `SnapShotCreator`
  - `SnapShotLister`
  - `SnapShotCommandRunner`

## Frontend (TypeScript/React)

### `snapshots.tsx`

- **Purpose**: React component that provides a user interface for managing snapshots.
- **Methods**:
  - `listSnapshots()`: Lists all snapshots.
  - `copySnapshot()`: Copies a snapshot.
  - `createSnapshot()`: Creates a snapshot.
  - `deleteSnapshot()`: Deletes a snapshot.
- **UI Elements**:
  - Buttons for creating, listing, updating, and deleting snapshots.
  - Dialog for confirming actions.

## Interaction Flow

1. **User Interaction**:
   - The user interacts with the React component (`snapshots.tsx`), clicking buttons to perform snapshot actions.

2. **Frontend to Backend**:
   - The React component calls methods on `SnapShotService` to perform the requested actions.

3. **Backend Processing**:
   - `SnapShotService` delegates the action to `SnapShotHandler`, which in turn uses the appropriate class (`SnapShotCreator`, `SnapShotLister`, `SnapShotDestroyer`, `SnapShotCloner`) to perform the task.

4. **Command Execution**:
   - If a Btrfs command is needed, `SnapShotCommandRunner` is used to execute the command.

5. **Result Handling**:
   - The results (e.g., list of snapshots) are sent back to the frontend and displayed to the user.

This setup ensures a clear separation of concerns, with each class performing a specific role and the frontend communicating with the backend through well-defined service methods.

## Sequence Diagram

![Snapshots Sequence Diagram](pumles/snapshots.png)
