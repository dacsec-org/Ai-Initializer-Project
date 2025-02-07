package org.dacss.projectinitai.metrics.utilities;

import jdk.jfr.Category;
import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
import reactor.core.publisher.Flux;
import java.time.Duration;

/**
 * <h1>{@link GpuStatsUtil}</h1>
 * Utility class to fetch GPU statistics.
 */
public class GpuStatsUtil {

    public GpuStatsUtil() {
    }

    public static Flux<Object> fetchGpuStats() {

        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> "GPU data at tick " + tick);
    }

}
