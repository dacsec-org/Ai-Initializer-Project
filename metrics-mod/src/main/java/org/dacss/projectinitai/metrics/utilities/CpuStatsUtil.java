package org.dacss.projectinitai.metrics.utilities;

import reactor.core.publisher.Flux;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.Duration;

/**
 * <h1>{@link CpuStatsUtil}</h1>
 * Utility class to fetch CPU statistics.
 */
public class CpuStatsUtil {
    private final OperatingSystemMXBean osBean;

    /**
     * <h3>{@link CpuStatsUtil}</h3>
     * 0-arg constructor to initialize the OperatingSystemMXBean.
     */
    public CpuStatsUtil() {
        this.osBean = ManagementFactory.getOperatingSystemMXBean();
    }

    /**
     * <h3>{@link #fetchCpuStats}</h3>
     * @return Flux<String> - CPU statistics
     */
    public Flux<String> fetchCpuStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(tick -> {
                       double cpuLoad = osBean.getSystemLoadAverage();
                       return "CPU Load at tick " + tick + ": " + cpuLoad;
                   });
    }
}
