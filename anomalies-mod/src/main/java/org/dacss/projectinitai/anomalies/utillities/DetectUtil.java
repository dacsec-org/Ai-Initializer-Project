package org.dacss.projectinitai.anomalies.utillities;

import org.dacss.projectinitai.anomalies.AnomalyTypes;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DetectUtil}</h1>
 * DetectUtil is a utility class that provides methods to detect anomalies.
 */
@Component
public class DetectUtil {

    public static Flux<Object> detectAnomaly() {
        //todo: implement anomaly detection logic
        return Flux.just("Anomaly detected");
    }
}
