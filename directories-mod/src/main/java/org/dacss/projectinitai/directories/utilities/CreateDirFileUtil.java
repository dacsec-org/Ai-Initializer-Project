package org.dacss.projectinitai.directories.utilities;
/**/
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link CreateDirFileUtil}</h1>
 * <p>
 * Utility class for creating directories and files.
 * </p>
 */
public class CreateDirFileUtil {

    private static final Logger log = LoggerFactory.getLogger(CreateDirFileUtil.class);

    /**
     * {@link #createDirectory(String)}
     *
     * @param path - the directory path
     */
    public static void createDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                log.error("Failed to create directory: {}", path);
            } else {
                log.info("Directory created successfully: {}", path);
            }
        } else {
            log.info("Directory already exists: {}", path);
        }
    }

    /**
     * {@link #createFile(String, String)}
     *
     * @param dirPath - the directory path
     * @param fileName - the file name
     */
    public static void createFile(String dirPath, String fileName) {
        File file = new File(dirPath, fileName);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    log.error("Failed to create file: {}", fileName);
                } else {
                    log.info("File created successfully: {}", fileName);
                }
            } catch (IOException fileCreateExc) {
                log.error("IOException occurred while creating file: {}",
                        fileName, fileCreateExc);
            }
        } else {
            log.info("File already exists: {}", fileName);
        }
    }
}
