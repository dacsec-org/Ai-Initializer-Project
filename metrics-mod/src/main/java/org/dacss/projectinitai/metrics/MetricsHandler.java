package org.dacss.projectinitai.metrics;
/**/

import org.dacss.projectinitai.metrics.services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link MetricsHandler}</h1>
 */
@Component
public class MetricsHandler implements MetricsIface {
    //todo: implement

    private final MetricsService metricsService;

    /**
     * <h2>{@link MetricsHandler#MetricsHandler(MetricsService)}</h2>
     * @param metricsService the metrics service.
     */
    @Autowired
    private MetricsHandler(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    /**
     * <h2>{@link MetricsIface#measure()}</h2>
     * measure the data.
     */
    @Override
    public void measure() {
    }
}
