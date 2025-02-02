package org.dacss.projectinitai.metrics.utilities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.io.File;
import java.time.Duration;

/**
 * <h1>{@link DiskStatsUtil}</h1>
 * Utility class to fetch disk statistics.
 */
@Component
public class DiskStatsUtil {

    public DiskStatsUtil() {
    }

    public static Flux<Object> fetchDiskStats() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(tick -> {
                       File root = new File("/");
                       long totalSpace = root.getTotalSpace();
                       long freeSpace = root.getFreeSpace();
                       long usableSpace = root.getUsableSpace();
                       return String.format("Total Space: %d, Free Space: %d, Usable Space: %d at tick %d",
                               totalSpace, freeSpace, usableSpace, tick);
                   });
    }
}
