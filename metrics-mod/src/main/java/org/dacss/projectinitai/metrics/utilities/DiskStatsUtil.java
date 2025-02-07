package org.dacss.projectinitai.metrics.utilities;

import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
import reactor.core.publisher.Flux;
import java.io.File;
import java.time.Duration;

/**
 * <h1>{@link DiskStatsUtil}</h1>
 * Utility class to fetch disk statistics.
 */
public class DiskStatsUtil  {

    public DiskStatsUtil() {
    }

    public static Flux<Object> fetchDiskStats() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> {
                    File[] roots = File.listRoots();
                    StringBuilder sb = new StringBuilder();
                    for (File root : roots) {
                        sb.append("File system root: ").append(root.getAbsolutePath());
                        sb.append(" Total space (bytes): ").append(root.getTotalSpace());
                        sb.append(" Free space (bytes): ").append(root.getFreeSpace());
                        sb.append(" Usable space (bytes): ").append(root.getUsableSpace());
                    }
                    return sb.toString();
                });
    }
}
