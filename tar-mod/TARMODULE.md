# Tar Module

The Tar Module provides functionality for creating, extracting, verifying, and deleting tar files in a Java project. This module consists of several classes and utilities that work together to handle tar file operations.

## Classes and Utilities

### `TarHandler`

The `TarHandler` class acts as a handler for tar file operations. It uses the `TarService` class to perform the actual operations.

#### Methods:
- `createTar(File sourceDir, File tarFile)`: Calls `TarService.createTar` to create a tar file.
- `extractTar(File tarFile, File destDir)`: Calls `TarService.extractTar` to extract a tar file.
- `deleteTar(File tarFile)`: Calls `TarService.deleteTar` to delete a tar file.
- `extractAndDestroyTar(File tarFile, File destDir)`: Calls `TarService.extractAndDestroyTar` to extract and then delete a tar file.

### `TarService`

The `TarService` class provides the core functionality for tar file operations. It is annotated with `@Service` and `@BrowserCallable`, making it callable from a Vaadin Hilla front-end.

#### Methods:
- `createTar(File sourceDir, File tarFile)`: Uses `TarCompressorUtil.
createTarFile` to create a tar file.
- `extractTar(File tarFile, File destDir)`: Uses `TarExtractorUtil.extractTarFile` to extract a tar file.
- `deleteTar(File tarFile)`: Checks if the tar file exists and has write permissions, then deletes it.
- `extractAndDestroyTar(File tarFile, File destDir)`: Extracts the tar 
  file and then deletes it using `TarDestroyUtil.destroyTarFile`.

### `TarCompressorUtil`

The `TarCompressorUtil` utility class is used for creating tar files.

#### Methods:
- `createTarFile(File sourceDir, File tarFile)`: Creates a tar file from the source directory.
- `addFilesToTar(TarArchiveOutputStream taos, File file, String parent)`: Recursively adds files to the tar archive.

### `TarExtractorUtil`

The `TarExtractorUtil` utility class is used for extracting tar files.

#### Methods:
- `extractTarFile(File tarFile, File destDir)`: Extracts the contents of a tar file to the destination directory.

### `TarDestroyUtil`

The `TarDestroyUtil` utility class is used for verifying and destroying 
tar files.

#### Methods:
- `verifyExtraction(File destDir)`: Verifies if the extraction of a tar file was successful by checking the destination directory.
- `destroyTarFile(File tarFile, File destDir)`: Verifies the extraction and deletes the tar file if the extraction is verified.
