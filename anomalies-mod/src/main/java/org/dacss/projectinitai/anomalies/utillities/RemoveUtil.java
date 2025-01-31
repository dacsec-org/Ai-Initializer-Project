package org.dacss.projectinitai.anomalies.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link RemoveUtil}</h1>
 * RemoveUtil is a utility class that provides methods to remove anomalies.
 */
@Component
public class RemoveUtil {

    public static Flux<Object> removeAnomaly() {
        //todo: implement anomaly removal logic
        return Flux.just("Anomaly removed");
    }
}
