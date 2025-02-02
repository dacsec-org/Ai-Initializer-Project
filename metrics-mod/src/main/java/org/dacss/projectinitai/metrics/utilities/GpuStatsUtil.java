package org.dacss.projectinitai.metrics.utilities;

import jdk.jfr.Category;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.time.Duration;

/**
 * <h1>{@link GpuStatsUtil}</h1>
 * Utility class to fetch GPU statistics.
 */
@Component
public class GpuStatsUtil {

    public GpuStatsUtil() {
    }

    public static Flux<Object> fetchGpuStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(tick -> {
                       // Use TornadoVM to fetch GPU data
                       return "GPU data at tick " + tick;
                   });
    }
}
