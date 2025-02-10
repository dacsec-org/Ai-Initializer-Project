package org.dacss.projectinitai.system;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * <h1>{@link MemoryCapSettings}</h1>
 * This class is used to get memory stats and set a cap on the memory usage for the framework.
 */
public class MemoryCapSettings {
    private static final OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    static long memoryCap;

    /**
     * Private constructor to prevent instantiation.
     */
    private MemoryCapSettings() {}

    /**
     * <h3>{@link #getTotalMemorySize()} </h3>
     *
     * @return The total physical memory size.
     */
    public static long getTotalMemorySize() {
        return osBean.getTotalMemorySize();
    }

    /**
     * <h3>{@link #getAllocatedMemorySize()} </h3>
     *
     * @return The allocated memory size.
     */
    public static long getAllocatedMemorySize() {
        return osBean.getTotalMemorySize() - osBean.getFreeMemorySize();
    }

    /**
     * <h3>{@link #setProjectMaxMemory()}</h3>
     * Sets the memory cap to 90% of the remaining memory after system allocated memory.
     */
    public static void setProjectMaxMemory() {
        long totalMemory = getTotalMemorySize();
        long allocatedMemory = getAllocatedMemorySize();
        long availableMemory = totalMemory - allocatedMemory;
        memoryCap = (long) (availableMemory * 0.9);
    }

    /**
     * <h3>{@link #getMemoryStats()}</h3>
     *
     * @return A Flux containing the stats of all memory-related methods.
     */
    public static Flux<Object> getMemoryStats() {
        return Flux.concat(
                Mono.just("Total Memory Size: " + getTotalMemorySize()),
                Mono.just("Allocated Memory Size: " + getAllocatedMemorySize()),
                Mono.just("Project Max Memory: " + memoryCap)
        );
    }

    /**
     * <h3>{@link #checkMemoryUsage()}</h3>
     *
     * @return A Flux that checks the memory usage and terminates the process if it exceeds the cap.
     */
    public static Flux<Object> checkMemoryUsage() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> {
                    long allocatedMemory = getAllocatedMemorySize();
                    if (allocatedMemory > memoryCap) {
                        System.err.println("Memory usage exceeded the cap. Terminating the process.");
                        System.exit(1);
                    }
                    return (Object) "Memory usage is within the cap.";
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * <h3>{@link #startMemoryMonitoring()}</h3>
     * <p>
     * Starts the memory usage monitoring.
     */
    public static void startMemoryMonitoring() {
        checkMemoryUsage().subscribe();
    }
}
