package org.dacss.projectinitai.system;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * <h1>{@link StorageCapSettings}</h1>
 * This class provides methods to retrieve various disk space metrics such as total disk size,
 * allocated disk space, default total space allocated, and minimum space allocation.
 * The results are returned as reactive streams using Project Reactor.
 */
public class StorageCapSettings {

    private static final long RESERVED_SPACE = 100L * 1024 * 1024 * 1024; // 100GB in bytes
    private static final List<String> PATHS = List.of("/", "/home");

    /**
     * Default 0-arg constructor.
     */
    public StorageCapSettings() {}

    /**
     * <h3>{@link #getTotalDiskSize()}</h3>
     * Retrieves the total disk size for the specified paths.
     *
     * @return a {@link Flux} emitting the total disk size in bytes for each path
     */
    public static Flux<Long> getTotalDiskSize() {
        return Flux.fromIterable(PATHS)
                .flatMap(path -> Mono.fromCallable(() -> {
                    try {
                        FileStore fileStore = Files.getFileStore(Paths.get(path));
                        return fileStore.getTotalSpace();
                    } catch (IOException e) {
                        return 0L;
                    }
                }).subscribeOn(Schedulers.boundedElastic()));
    }

    /**
     * <h3>{@link #getAllocatedDiskSpace()}</h3>
     * Retrieves the allocated disk space for the specified paths.
     *
     * @return a {@link Flux} emitting the allocated disk space in bytes for each path
     */
    public static Flux<Long> getAllocatedDiskSpace() {
        return Flux.fromIterable(PATHS)
                .flatMap(path -> Mono.fromCallable(() -> {
                    try {
                        FileStore fileStore = Files.getFileStore(Paths.get(path));
                        return fileStore.getTotalSpace() - fileStore.getUnallocatedSpace();
                    } catch (IOException e) {
                        return 0L;
                    }
                }).subscribeOn(Schedulers.boundedElastic()));
    }

    /**
     * <h3>{@link #getDefaultTotalSpaceAllocated()}</h3>
     * Retrieves the default total space allocated for the specified paths.
     * The default total space allocated is calculated as the minimum of the unallocated space
     * and the maximum allowed space (total space minus reserved space).
     *
     * @return a {@link Flux} emitting the default total space allocated in bytes for each path
     */
    public static Flux<Long> getDefaultTotalSpaceAllocated() {
        return Flux.fromIterable(PATHS)
                .flatMap(path -> Mono.fromCallable(() -> {
                    try {
                        FileStore fileStore = Files.getFileStore(Paths.get(path));
                        long totalSpace = fileStore.getTotalSpace();
                        long unallocatedSpace = fileStore.getUnallocatedSpace();
                        long maxAllowed = totalSpace - RESERVED_SPACE;
                        return Math.min(unallocatedSpace, maxAllowed);
                    } catch (IOException e) {
                        return 0L;
                    }
                }).subscribeOn(Schedulers.boundedElastic()));
    }

    /**
     * <h3>{@link #getMinimumSpaceAllocation()}</h3>
     * Retrieves the minimum space allocation.
     *
     * @return a {@link Mono} emitting the minimum space allocation in bytes
     */
    public static Mono<Long> getMinimumSpaceAllocation() {
        return Mono.just(20L * 1024 * 1024 * 1024); // 20GB in bytes
    }

    /**
     * <h3>{@link #getResults()}</h3>
     * Retrieves all disk space metrics (total disk size, allocated disk space, default total space allocated,
     * and minimum space allocation) as a concatenated stream.
     *
     * @return a {@link Flux} emitting all disk space metrics as objects
     */
    public static Flux<Object> getResults() {
        return Flux.concat(
                getTotalDiskSize().map(size -> size),
                getAllocatedDiskSpace().map(size -> size),
                getDefaultTotalSpaceAllocated().map(size -> size),
                getMinimumSpaceAllocation().map(size -> size)
        );
    }
}
