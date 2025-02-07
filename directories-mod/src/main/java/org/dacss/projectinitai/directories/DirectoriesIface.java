package org.dacss.projectinitai.directories;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DirectoriesIface}</h1>
 * <code>@FunctionalInterface</code> for handling directory and file operations.
 * This interface defines a method for processing directory and file actions such as creating or deleting directories and files.
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface DirectoriesIface {

    /**
     * <h3>{@link #processDirFile(DirectoryActions, String, String)}</h3>
     * Processes a directory or file action based on the specified parameters.
     *
     * @param action   the directory action to be performed
     * @param path     the path of the directory or file
     * @param fileName the name of the file (can be null if the action is directory-related)
     * @return a Flux stream of objects representing the action status or result
     */
    Flux<Object> processDirFile(DirectoryActions action, String path, String fileName);
}
