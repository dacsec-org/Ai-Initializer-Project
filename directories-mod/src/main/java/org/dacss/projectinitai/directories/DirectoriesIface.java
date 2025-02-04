package org.dacss.projectinitai.directories;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link DirectoriesIface}</h1>
 * Interface for directory and file operations.
 * This interface provides methods to process directory and file actions in a non-blocking manner using Reactor.
 */
@FunctionalInterface
public interface DirectoriesIface {

    Flux<Object> processDirFile(DirectoryActions action, String path, String fileName);
}
