package org.dacss.projectinitai.metrics;

import org.dacss.projectinitai.metrics.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link MetricsIface}</h1>
 */
@FunctionalInterface
public interface MetricsIface {

    Flux<Object> measure(MetricsTypes type);
}
