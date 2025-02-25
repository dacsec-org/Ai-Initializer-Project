package org.dacss.projectinitai.directories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * <h1>{@link DirFileUtil}</h1>
 * Handles directory and file operations.
 */
public class DirFileUtil {

    private static final Logger log = LoggerFactory.getLogger(DirFileUtil.class);
    private static final String USER_HOME = System.getProperty("user.home");

    /**
     * <h3>{@link #DirFileUtil()}</h3>
     * Default 0-arg constructor.
     */
    public DirFileUtil() {}

    /**
     * <h3>{@link #createDirectory(String)}</h3>
     * Creates a directory at the specified path.
     *
     * @param path the path of the directory to be created
     * @return a Flux stream representing the creation status
     */
    public static Flux<Object> createDirectory(String path) {
        return Flux.create(sink -> {
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
            sink.next(new Object());
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #createFile(String, String)}</h3>
     * Creates a file with the specified name in the given directory.
     *
     * @param dirPath the path of the directory
     * @param fileName the name of the file to be created
     * @return a Flux stream representing the creation status
     */
    public static Flux<Object> createFile(String dirPath, String fileName) {
        return Flux.create(sink -> {
            File file = new File(dirPath, fileName);
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        log.error("Failed to create file: {}", fileName);
                    } else {
                        log.info("File created successfully: {}", fileName);
                    }
                    sink.next(new Object());
                    sink.complete();
                } catch (IOException fileCreateExc) {
                    log.error("IOException occurred while creating file: {}", fileName, fileCreateExc);
                    sink.error(fileCreateExc);
                }
            } else {
                log.info("File already exists: {}", fileName);
                sink.next(new Object());
                sink.complete();
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #deleteDirectory(String)}</h3>
     * Deletes the directory at the specified path.
     *
     * @param path the path of the directory to be deleted
     * @return a Flux stream representing the deletion status
     */
    public static Flux<Object> deleteDirectory(String path) {
        return Flux.create(sink -> {
            File dir = new File(path);
            if (dir.exists()) {
                deleteRecursively(dir);
            } else {
                log.info("Directory does not exist: {}", path);
            }
            sink.next(new Object());
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #deleteFile(String, String)}</h3>
     * Deletes the file with the specified name in the given directory.
     *
     * @param dirPath the path of the directory
     * @param fileName the name of the file to be deleted
     * @return a Flux stream representing the deletion status
     */
    public static Flux<Object> deleteFile(String dirPath, String fileName) {
        return Flux.create(sink -> {
            File file = new File(dirPath, fileName);
            if (file.exists()) {
                if (!file.delete()) {
                    log.error("Failed to delete file: {}", fileName);
                } else {
                    log.info("File deleted successfully: {}", fileName);
                }
            } else {
                log.info("File does not exist: {}", fileName);
            }
            sink.next(new Object());
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #deleteRecursively(File)}</h3>
     * Recursively deletes the specified file or directory.
     *
     * @param file the file or directory to be deleted
     */
    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteRecursively(f);
                }
            }
        }
        if (!file.delete()) {
            log.error("Failed to delete: {}", file.getPath());
        } else {
            log.info("Deleted successfully: {}", file.getPath());
        }
    }

    /**
     * <h3>{@link #getTargetPath(String, String, String)}</h3>
     * Constructs the target path for a file based on the root directory, LLM name, and file name.
     *
     * @param rootDir the root directory
     * @param llmName the name of the LLM
     * @param fileName the name of the file
     * @return the constructed target path
     */
    public Path getTargetPath(String rootDir, String llmName, String fileName) {
        String subDir;
        if (fileName.endsWith(".json") || fileName.endsWith(".txt")) {
            subDir = "configs";
        } else if (fileName.equals(".gitattributes") || fileName.equals("LICENSE") || fileName.equals("README.md")) {
            subDir = "info";
        } else if (fileName.startsWith("model.")) {
            subDir = "model";
        } else {
            subDir = "checksums";
        }
        return Paths.get(USER_HOME).resolve(Paths.get(".project-ai-initializer/models", llmName, subDir, fileName)).normalize();
    }
}
