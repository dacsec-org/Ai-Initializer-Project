package org.dacss.projectinitai.metrics.utilities;

import reactor.core.publisher.Flux;
import java.time.Duration;

/**
 * <h1>{@link ServerStatsUtil}</h1>
 * Utility class to fetch server statistics.
 */
public class ServerStatsUtil {

    /**
     * <h3>{@link #fetchServerStats}</h3>
     * @return Flux<String> - Server statistics
     */
    public Flux<String> fetchServerStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(tick -> "Server stats are currently unavailable.");
    }
}
