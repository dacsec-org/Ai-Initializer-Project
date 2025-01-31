package org.dacss.projectinitai.anomalies;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link AnomaliesIface}</h1>
 */
@FunctionalInterface
public interface AnomaliesIface {

    Flux<Object> detectAnomaly(AnomalyTypes type);
}
