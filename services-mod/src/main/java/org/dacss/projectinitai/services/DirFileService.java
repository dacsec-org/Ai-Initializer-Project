package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.directories.DirectoriesIface;
import org.dacss.projectinitai.directories.utilities.CreateDirFileUtil;
import org.dacss.projectinitai.directories.utilities.DestroyDirFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * <h1>{@link DirFileService}</h1>
 * <p>
 * Backend hilla endpoint service for directories and files.
 * </p>
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class DirFileService implements DirectoriesIface {

    private static final Logger log = LoggerFactory.getLogger(DirFileService.class);

    /**
     * <h2>{@link #DirFileService()}</h2>
     * 0-arg constructor.
     */
    public DirFileService() {}

    /**
     * <h2>{@link #processDirFileAction(String, String, String)}</h2>
     * Perform directory and file operations. via the functional interface {@link DirectoriesIface}.
     *
     * @param action The action to perform.
     * @param path The directory path.
     * @param fileName The file name (optional, can be null).
     */
    @Override
    public void processDirFileAction(String action, String path, String fileName) {
        try {
            switch (action.toLowerCase()) {
                case "create_directory":
                    CreateDirFileUtil.createDirectory(path);
                    break;
                case "create_file":
                    CreateDirFileUtil.createFile(path, fileName);
                    break;
                case "delete_directory":
                    DestroyDirFileUtil.deleteDirectory(path);
                    break;
                case "delete_file":
                    DestroyDirFileUtil.deleteFile(path, fileName);
                    break;
                default:
                    throw new IllegalArgumentException(MessageFormat.format("Invalid action: {0}", action));
            }
        } catch (Exception dirFileExc) {
            log.error("Error handling operation: {}", action, dirFileExc);
        }
    }
}
