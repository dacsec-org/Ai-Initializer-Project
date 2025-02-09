package org.dacss.projectinitai.system;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link MemoryCapSettings}</h1>
 * This class is used to get memory stats and set a cap on the memory usage for the framework.
 */
public class MemoryCapSettings {
    private static final OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private static long memoryCap;

    /**
     * <h3>{@link #MemoryCapSettings()}</h3>
     * Private constructor to prevent instantiation.
     */
    private MemoryCapSettings() {}

    /**
     * <h3>{@link #getMemoryCapSettings()}</h3>
     * Returns the current memory cap settings.
     *
     * @return A Flux containing the current memory cap settings.
     */
    public static Flux<Object> getMemoryCapSettings() {
        return Flux.just(memoryCap);
    }

    /**
     * <h3>{@link #setMemoryCapPercentage(int)}</h3>
     * Sets the memory cap as a percentage of the total available memory.
     *
     * @param percentage The percentage of total memory to set as the cap.
     */
    public static void setMemoryCapPercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        long totalMemory = osBean.getTotalMemorySize();
        memoryCap = totalMemory * percentage / 100;
    }

    /**
     * <h3>{@link #getTotalMemory()}</h3>
     * Returns the total physical memory size for frontend use.
     *
     * @return The total physical memory size.
     */
    public static long getTotalMemory() {
        return osBean.getTotalMemorySize();
    }
}
