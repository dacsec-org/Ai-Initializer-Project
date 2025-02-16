package org.dacss.projectinitai.anomalies.utillities;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link RemediateUtil}</h1>
 * RemediateUtil is a utility class that provides methods to remediate anomalies.
 */
public class RemediateUtil {

    public static Flux<Object> repairAnomaly() {
        //todo: implement anomaly repair logic
        return Flux.just("Anomaly repaired");
    }
}
