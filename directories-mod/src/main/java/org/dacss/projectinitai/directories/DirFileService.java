package org.dacss.projectinitai.directories;
/**/
import com.vaadin.hilla.BrowserCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link DirFileService}</h1>
 * <p>
 * Backend hilla endpoint service for directories and files.
 * </p>
 */
@Service
@BrowserCallable
public class DirFileService {

    private static final Logger log = LoggerFactory.getLogger(DirFileService.class);
    private final DirFileHandler dirFileHandler;

    /**
     * <h2>{@link #DirFileService()}</h2>
     * 0-arg constructor.
     */
    public DirFileService() {
        this.dirFileHandler = new DirFileHandler();
    }


    public void handleOperation(Operation operation, String path, String fileName) {
        try {
            switch (operation) {
                case CREATE_DIRECTORY:
                    dirFileHandler.createDirectory(path);
                    break;
                case CREATE_FILE:
                    dirFileHandler.createFile(path, fileName);
                    break;
                case DELETE_DIRECTORY:
                    dirFileHandler.deleteDirectory(path);
                    break;
                case DELETE_FILE:
                    dirFileHandler.deleteFile(path, fileName);
                    break;
                default:
                    throw new IllegalArgumentException(STR."Unsupported operation: \{operation}");
            }
        } catch (Exception dirFileExc) {
            log.error("Error handling operation: {}", operation, dirFileExc);
        }
    }
}
