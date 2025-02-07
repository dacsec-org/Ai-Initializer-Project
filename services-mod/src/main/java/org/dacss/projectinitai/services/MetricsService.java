package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
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

    public MetricsService() {}

    @Override
    public Flux<Object> measure(MetricsTypes type) {
        Flux<Object> flux;
        try {
            flux = switch (type) {
                case DISK -> DiskStatsUtil.fetchDiskStats();
                case MEMORY -> MemoryStatsUtil.fetchMemoryStats();
                case GPU -> GpuStatsUtil.fetchGpuStats();
                case NETWORK -> NetworkStatsUtil.fetchNetworkStats();
                case SERVER -> ServerStatsUtil.fetchServerStats();
                case CPU -> CpuStatsUtil.fetchCpuStats();
            };
        } catch (Exception metricsExc) {
            log.error("{}: {}", type, metricsExc.getMessage());
            return Flux.empty();
        } finally {
            log.info("{}: Metrics fetched successfully", type);
        }
        return flux;
    }
}
