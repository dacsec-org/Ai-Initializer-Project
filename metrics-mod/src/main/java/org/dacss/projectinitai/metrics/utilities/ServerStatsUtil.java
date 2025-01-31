package org.dacss.projectinitai.metrics.utilities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.time.Duration;

/**
 * <h1>{@link ServerStatsUtil}</h1>
 * Utility class to fetch server statistics.
 */
@Component
public class ServerStatsUtil {

    /**
     * <h3>{@link #fetchServerStats}</h3>
     * @return Flux<String> - Server statistics
     */
    public static Flux<Object> fetchServerStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(tick -> "Server stats are currently unavailable.");
    }
}
