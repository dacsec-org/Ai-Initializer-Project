package org.dacss.projectinitai.directories;

import reactor.core.publisher.Flux;

@FunctionalInterface
public interface DirectoriesIface {

    Flux<Object> processDirFile(DirectoryActions action, String path, String fileName);
}
