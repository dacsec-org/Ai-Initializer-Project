package org.dacss.projectinitai.metrics.utilities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.Duration;
import java.util.stream.IntStream;

/**
 * <h1>{@link CpuStatsUtil}</h1>
 * Utility class to fetch CPU statistics.
 */
@Component
public class CpuStatsUtil {
    private static OperatingSystemMXBean osBean;
    private static int availableProcessors;

    public CpuStatsUtil() {
        osBean = ManagementFactory.getOperatingSystemMXBean();
        availableProcessors = osBean.getAvailableProcessors();
    }

    public static Flux<Object> fetchCpuStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .flatMap(tick -> Flux.fromStream(IntStream.range(0, availableProcessors)
                       .mapToObj(core -> {
                           double cpuLoad = osBean.getSystemLoadAverage() / availableProcessors;
                           return "CPU Load at tick " + tick + " for core " + core + ": " + cpuLoad;
                       })));
    }
}
