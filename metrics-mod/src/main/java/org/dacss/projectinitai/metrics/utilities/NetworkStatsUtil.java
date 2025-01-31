package org.dacss.projectinitai.metrics.utilities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.Duration;
import java.util.Collections;

/**
 * <h1>{@link NetworkStatsUtil}</h1>
 * Utility class to fetch network statistics.
 */
@Component
public class NetworkStatsUtil {


    public static Flux<Object> fetchNetworkStats() {
        return Flux.interval(Duration.ofSeconds(1))
                .publishOn(Schedulers.boundedElastic())
                   .map(tick -> {
                       StringBuilder stats = new StringBuilder();
                       try {
                           for (NetworkInterface netInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                               stats.append(String.format("Interface: %s, Bytes Sent: %d, Bytes Received: %d%n",
                                       netInterface.getDisplayName(),
                                       netInterface.getMTU(), // Placeholder for bytes sent
                                       netInterface.getMTU()  // Placeholder for bytes received
                               ));
                           }
                       } catch (SocketException e) {
                           stats.append("Error fetching network stats: ").append(e.getMessage());
                       }
                       return stats.toString();
                   });
    }
}
