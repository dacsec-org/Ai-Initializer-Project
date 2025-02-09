package org.dacss.projectinitai.system;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * <h1>{@link StorageCapSettings}</h1>
 * This class provides methods to query and cap the storage capacity of the framework.
 */
public class StorageCapSettings {

    /**
     * Project target directories to calculate storage usage.
     */
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

    /**
     * Project target files to calculate storage usage.
     */
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
     * Retrieves the current storage cap settings.
     *
     * @return A {@link Mono} containing a string representation of the total and used storage.
     */
    public static Mono<Object> getStorageCapSettings() {
        return Mono.zip(getTotalStorage(), getUsedStorage())
                .map(tuple ->
                        "Total Storage: " + tuple.getT1() + " bytes, Used Storage: " + tuple.getT2() + " bytes");
    }

    /**
     * <h3>{@link #getTotalStorage()}</h3>
     * Retrieves the total storage available on the system.
     *
     * @return A {@link Mono} containing the total storage in bytes.
     */
    public static Mono<Long> getTotalStorage() {
        return Mono.fromCallable(() -> {
            try {
                FileStore fileStore = Files.getFileStore(Paths.get("/"));
                return fileStore.getTotalSpace();
            } catch (IOException getTotalStorageExc) {
                return 0L;
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #getUsedStorage()}</h3>
     * Retrieves the total storage used by the application and its directories.
     *
     * @return A {@link Mono} containing the total used storage in bytes.
     */
    public static Mono<Long> getUsedStorage() {
        return Flux.fromIterable(PROJECT_DIRS)
                .flatMap(dir -> getDirectorySize(Paths.get(dir)))
                .concatWith(Flux.fromIterable(PROJECT_FILES)
                        .flatMap(file -> getFileSize(Paths.get(file))))
                .reduce(Long::sum);
    }

    /**
     * <h3>{@link #getDirectorySize(Path)}</h3>
     * Calculates the size of a directory.
     *
     * @param path The path to the directory.
     * @return A {@link Mono} containing the size of the directory in bytes.
     */
    private static Mono<Long> getDirectorySize(@NotNull Path path) {
        return Mono.<Long>create(sink -> {
            final long[] size = {0};
            try {
                Files.walkFileTree(path, new SimpleFileVisitor<>() {
                    public @NotNull FileVisitResult visitFile(Path file, @NotNull BasicFileAttributes attrs) {
                        if (file != null) {
                            size[0] += attrs.size();
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    public @NotNull FileVisitResult visitFileFailed(Path file, @NotNull IOException exc) {
                        return FileVisitResult.CONTINUE;
                    }
                });
                sink.success(size[0]);
            } catch (IOException getDirectorySizeExc) {
                sink.success(0L);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #getFileSize(Path)}</h3>
     * Calculates the size of a file.
     *
     * @param path The path to the file.
     * @return A {@link Mono} containing the size of the file in bytes.
     */
    private static Mono<Long> getFileSize(Path path) {
        if (path == null) {
            return Mono.empty();
        }
        return Mono.fromCallable(() -> {
            try {
                return Files.size(path);
            } catch (IOException getFileSizeExc) {
                return 0L;
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
