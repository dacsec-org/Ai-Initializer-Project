package org.dacss.projectinitai.services;

import org.dacss.projectinitai.annotations.Bridge;
import org.dacss.projectinitai.directories.DirFileUtil;
import org.dacss.projectinitai.directories.DirectoriesIface;
import org.dacss.projectinitai.directories.DirectoryActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DirFileService}</h1>
 */
@Service
@Bridge("dir-file-service")
public class DirFileService implements DirectoriesIface {

    private static final Logger log = LoggerFactory.getLogger(DirFileService.class);

    public DirFileService() {}

    @Override
    public Flux<Object> processDirFile(DirectoryActions action, String path, String fileName) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case CREATE_DIRECTORY -> DirFileUtil.createDirectory(path);
                case CREATE_FILE -> DirFileUtil.createFile(path, fileName);
                case DELETE_DIRECTORY -> DirFileUtil.deleteDirectory(path);
                case DELETE_FILE -> DirFileUtil.deleteFile(path, fileName);

            };
            log.info("{}: {}", action, path);
        } catch (Exception dirFileExc) {
            return Flux.empty();
        }
        return flux;

    }
}
