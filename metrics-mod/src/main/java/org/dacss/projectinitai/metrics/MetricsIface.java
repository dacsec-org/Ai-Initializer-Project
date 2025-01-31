package org.dacss.projectinitai.metrics;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link MetricsIface}</h1>
 */
@FunctionalInterface
public interface MetricsIface {

    Flux<Object> measure(MetricsTypes type);
}
