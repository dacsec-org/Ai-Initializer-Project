package org.dacss.projectinitai.services.metrics;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link MetricsService}</h1>
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class MetricsService implements MetricsIface {

    private static final Logger log = LoggerFactory.getLogger(MetricsService.class);

    @Override
    public void measure(String action) {
        Flux<String> flux;
        switch (action) {
            case "gpu":
                flux = new GpuStatsUtil().fetchGpuStats();
                break;
            case "cpu":
                flux = new CpuStatsUtil().fetchCpuStats();
                break;
            case "memory":
                flux = new MemoryStatsUtil().fetchMemoryStats();
                break;
            case "disk":
                flux = new DiskStatsUtil().fetchDiskStats();
                break;
            case "network":
                flux = new NetworkStatsUtil().fetchNetworkStats();
                break;
            default:
                log.error("Invalid metric type: {}", action);
                return;
        }
        flux.subscribe(value -> log.info("{} Stats: {}", action.toUpperCase(), value));
    }
}
