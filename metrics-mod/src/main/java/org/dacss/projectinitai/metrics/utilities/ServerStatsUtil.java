package org.dacss.projectinitai.metrics.utilities;

import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
import reactor.core.publisher.Flux;
import java.time.Duration;

/**
 * <h1>{@link ServerStatsUtil}</h1>
 * Utility class to fetch server statistics.
 */
public class ServerStatsUtil {


    public ServerStatsUtil() {
    }

    public static Flux<Object> fetchServerStats() {

        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> "Server data at tick " + tick);
    }
}
