package org.dacss.projectinitai.metrics.utilities;

import reactor.core.publisher.Flux;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.Duration;
import java.util.stream.IntStream;

/**
 * <h1>{@link CpuStatsUtil}</h1>
 * Utility class to fetch CPU statistics.
 */
public class CpuStatsUtil {
    private final OperatingSystemMXBean osBean;
    private final int availableProcessors;

    /**
     * <h3>{@link CpuStatsUtil}</h3>
     * 0-arg constructor to initialize the OperatingSystemMXBean.
     */
    public CpuStatsUtil() {
        this.osBean = ManagementFactory.getOperatingSystemMXBean();
        this.availableProcessors = osBean.getAvailableProcessors();
    }

    /**
     * <h3>{@link #fetchCpuStats}</h3>
     * @return Flux<String> - CPU statistics for each core
     */
    public Flux<String> fetchCpuStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .flatMap(tick -> Flux.fromStream(IntStream.range(0, availableProcessors)
                       .mapToObj(core -> {
                           double cpuLoad = osBean.getSystemLoadAverage() / availableProcessors;
                           return "CPU Load at tick " + tick + " for core " + core + ": " + cpuLoad;
                       })));
    }
}
