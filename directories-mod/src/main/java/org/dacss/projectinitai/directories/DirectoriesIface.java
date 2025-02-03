package org.dacss.projectinitai.directories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DirectoriesIface}</h1>
 * Interface for directory and file operations.
 * This interface provides methods to process directory and file actions in a non-blocking manner using Reactor.
 */
@FunctionalInterface
public interface DirectoriesIface {

    Logger log = LoggerFactory.getLogger(DirectoriesIface.class);
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    /**
     * <h3>{@link DirectoryActions}</h3>
     * Processes a directory or file action.
     * This method handles the creation and deletion of directories and files based on the specified action.
     * It logs the completion or error of the operation.
     *
     * @param action   the action to perform (create or delete directory/file)
     * @param path     the path of the directory or file
     * @param fileName the name of the file (optional, can be null for directory actions)
     * @return a {@link Flux} that completes when the action is processed
     */
    static Flux<Object> processDirFileAction(DirectoryActions action, String path, String fileName) {
        try {
            Flux<Object> result = switch (action) {
                case CREATE_DIRECTORY -> CreateDirFileUtil.createDirectory(path);
                case CREATE_FILE -> CreateDirFileUtil.createFile(path, fileName);
                case DELETE_DIRECTORY -> DestroyDirFileUtil.deleteDirectory(path);
                case DELETE_FILE -> DestroyDirFileUtil.deleteFile(path, fileName);
            };
            log.info(GREEN + "Operation completed: {}" + RESET, action);
            return result;
        } catch (Exception dirFileExc) {
            log.error(RED + "Error handling operation: {}" + RESET, action, dirFileExc);
            return Flux.error(new RuntimeException(RED + "Error handling operation: " + action + RESET, dirFileExc));
        }
    }

    /**
     * <h3>{@link #processDirFile(DirectoryActions, String, String)}</h3>
     * Processes a directory or file action.
     * This method is intended to be implemented by classes that use this interface.
     *
     * @param action   the action to perform (create or delete directory/file)
     * @param path     the path of the directory or file
     * @param fileName the name of the file (optional, can be null for directory actions)
     * @return a {@link Flux} that completes when the action is processed
     */
    Flux<Object> processDirFile(DirectoryActions action, String path, String fileName);
}
