package org.dacss.projectinitai.metrics.utilities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.Duration;

/**
 * <h1>{@link MemoryStatsUtil}</h1>
 * Utility class to fetch memory statistics.
 */
@Component
public class MemoryStatsUtil {
    private static MemoryMXBean memoryBean;

    /**
     * <h3>{@link MemoryStatsUtil}</h3>
     * 0-arg constructor to initialize the MemoryMXBean.
     */
    public MemoryStatsUtil() {
        memoryBean = ManagementFactory.getMemoryMXBean();
    }

    /**
     * <h3>{@link #fetchMemoryStats}</h3>
     * @return Flux<String> - Memory statistics
     */
    public static Flux<Object> fetchMemoryStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(tick -> {
                       MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
                       MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();
                       return String.format("Heap Memory: %d/%d, Non-Heap Memory: %d/%d at tick %d",
                               heapUsage.getUsed(), heapUsage.getMax(),
                               nonHeapUsage.getUsed(), nonHeapUsage.getMax(),
                               tick);
                   });
    }
}
