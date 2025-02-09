package org.dacss.projectinitai.system;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link StorageCapSettings}</h1>
 * Queries, and caps the storage capacity of the framework.
 */
public class StorageCapSettings {
    /*FIXME: Not annotated method overrides method annotated with @NotNull96,104
           Not annotated parameter overrides @NotNull parameter96,104*/

    private static final List<String> PROJECT_DIRS = Arrays.asList(
        "/etc/project-ai-initializer",
        "/etc/systemd/project-ai-initializer",
        "/usr/share/applications",
        "/home/" + System.getProperty("user.name") + "/.project-ai-initializer/models",
        "/home/" + System.getProperty("user.name") + "/.project-ai-initializer/checksums",
        "/home/" + System.getProperty("user.name") + "/TornadoVM",
        "/home/" + System.getProperty("user.name") + "/project-ai-initializer.cache",
        "/opt/project-ai-initializer",
        "/var/run/project-ai-initializer",
        "/var/log/project-ai-initializer",
        "/etc/security"
    );

    private static final List<String> PROJECT_FILES = Arrays.asList(
        "/etc/project-ai-initializer/project-ai-initializer.conf",
        "/systemd/project-ai-initializer/project-ai-initializer.service",
        "/usr/share/applications/project-ai-initializer.desktop",
        "/var/run/project-ai-initializer/project-ai-initializer.sock",
        "/var/log/project-ai-initializer/project-ai-initializer.log"
    );

    /**
     * <h3>{@link #StorageCapSettings()}</h3>
     * Private constructor to prevent instantiation.
     */
    private StorageCapSettings() {}

    /**
     * <h3>{@link #getStorageCapSettings()}</h3>
     * Returns the current storage cap settings.
     *
     * @return A Mono containing the current storage cap settings.
     */
    public static Mono<Object> getStorageCapSettings() {
        long totalStorage = getTotalStorage();
        long usedStorage = getUsedStorage();
        return Mono.just("Total Storage: " + totalStorage + " bytes, Used Storage: " + usedStorage + " bytes");
    }

    /**
     * <h3>{@link #getTotalStorage()}</h3>
     * Returns the total storage available on the system.
     *
     * @return The total storage available on the system.
     */
    public static long getTotalStorage() {
        FileStore fileStore;
        try {
            fileStore = Files.getFileStore(Paths.get("/"));
            return fileStore.getTotalSpace();
        } catch (IOException e) {
            throw new RuntimeException("Failed to get total storage", e);
        }
    }

    /**
     * <h3>{@link #getUsedStorage()}</h3>
     * Returns the total storage used by the application and its directories.
     *
     * @return The total storage used by the application and its directories.
     */
    public static long getUsedStorage() {
        long totalSize = 0;
        for (String dir : PROJECT_DIRS) {
            totalSize += getDirectorySize(Paths.get(dir));
        }
        for (String file : PROJECT_FILES) {
            totalSize += getFileSize(Paths.get(file));
        }
        return totalSize;
    }


    private static long getDirectorySize(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        final long[] size = {0};
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file == null || attrs == null) {
                        return FileVisitResult.CONTINUE;
                    }
                    size[0] += attrs.size();
                    return FileVisitResult.CONTINUE;
                }

                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to get directory size for " + path, e);
        }
        return size[0];
    }

    private static long getFileSize(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        try {
            return Files.size(path);
        } catch (IOException e) {
            return 0;
        }
    }
}
