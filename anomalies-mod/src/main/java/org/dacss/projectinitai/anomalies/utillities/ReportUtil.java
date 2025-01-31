package org.dacss.projectinitai.anomalies.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ReportUtil}</h1>
 * ReportUtil is a utility class that provides methods to generate reports.
 */
@Component
public class ReportUtil {

    public static Flux<Object> reportAnomaly() {
        //todo: implement reportAnomaly
        return Flux.just("reportAnomaly");
    }
}
