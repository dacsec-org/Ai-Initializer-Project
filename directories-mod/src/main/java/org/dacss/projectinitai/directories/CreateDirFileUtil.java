package org.dacss.projectinitai.directories;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * <h1>{@link CreateDirFileUtil}</h1>
 * Utility class for creating directories and files.
 * This class provides methods to create directories and files in a non-blocking manner using Reactor.
 */
@Component
public class CreateDirFileUtil {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CreateDirFileUtil.class);
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String RESET = "\u001B[0m";

    /**
     * <h3>{@link #CreateDirFileUtil()}</h3>
     * Default 0-arg constructor.
     */
    public CreateDirFileUtil() {}

    /**
     * <h3>{@link #createDirectory(String)}</h3>
     * Creates a directory at the specified path.
     * If the directory already exists, it logs a message indicating so.
     * This method runs on a separate thread to avoid blocking the main thread.
     *
     * @param path the directory path
     * @return a Flux that completes when the directory is created
     */
    public static Flux<Object> createDirectory(String path) {
        return Flux.create(sink -> {
            File dir = new File(path);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    log.error("{}Failed to create directory: {}{}", RED, path, RESET);
                } else {
                    log.info("{}Directory created successfully: {}{}", GREEN, path, RESET);
                }
            } else {
                log.info("{}Directory already exists: {}{}", GREEN, path, RESET);
            }
            sink.next(new Object());
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #createFile(String, String)}</h3>
     * Creates a file with the specified name in the specified directory.
     * If the file already exists, it logs a message indicating so.
     * This method runs on a separate thread to avoid blocking the main thread.
     *
     * @param dirPath the directory path
     * @param fileName the file name
     * @return a Flux that completes when the file is created
     */
    public static Flux<Object> createFile(String dirPath, String fileName) {
        return Flux.create(sink -> {
            File file = new File(dirPath, fileName);
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        log.error("{}Failed to create file: {}{}", RED, fileName, RESET);
                    } else {
                        log.info("{}File created successfully: {}{}", GREEN, fileName, RESET);
                    }
                    sink.next(new Object());
                    sink.complete();
                } catch (IOException fileCreateExc) {
                    log.error("IOException occurred while creating file: {}", fileName, fileCreateExc);
                    sink.error(fileCreateExc);
                }
            } else {
                log.info("{}File already exists: {}{}", GREEN, fileName, RESET);
                sink.next(new Object());
                sink.complete();
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
