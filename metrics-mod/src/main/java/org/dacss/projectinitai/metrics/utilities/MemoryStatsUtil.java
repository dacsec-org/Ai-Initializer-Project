package org.dacss.projectinitai.metrics.utilities;

import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
import reactor.core.publisher.Flux;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.Duration;

/**
 * <h1>{@link MemoryStatsUtil}</h1>
 * Utility class to fetch memory statistics.
 */
public class MemoryStatsUtil {
    private static MemoryMXBean memoryBean;

    public MemoryStatsUtil() {
        memoryBean = ManagementFactory.getMemoryMXBean();
    }

    public static Flux<Object> fetchMemoryStats() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> {
                    MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
                    MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();
                    return "Heap Memory Usage at tick " + tick + ": " + heapMemoryUsage.toString() + "\n" +
                            "Non-Heap Memory Usage at tick " + tick + ": " + nonHeapMemoryUsage.toString();
                });
    }
}
