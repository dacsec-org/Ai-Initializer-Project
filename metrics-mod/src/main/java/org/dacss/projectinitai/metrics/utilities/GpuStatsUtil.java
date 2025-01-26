package org.dacss.projectinitai.metrics.utilities;

import reactor.core.publisher.Flux;
import java.time.Duration;

public class GpuStatsUtil {
    public Flux<String> fetchGpuStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(tick -> {
                       // Use TornadoVM to fetch GPU data
                       return "GPU data at tick " + tick;
                   });
    }
}
