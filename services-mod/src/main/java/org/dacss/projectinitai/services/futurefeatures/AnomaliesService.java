package org.dacss.projectinitai.services.futurefeatures;
/**/
import org.dacss.projectinitai.annotations.Bridge;
import org.dacss.projectinitai.anomalies.AnomaliesIface;
import org.dacss.projectinitai.anomalies.AnomalyTypes;
import org.dacss.projectinitai.anomalies.utillities.*;
/**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link AnomaliesService}</h1>
 * Backend service, use functional interface as a switch-statement for detecting anomalies.
 */
@Service
@Bridge("anomalies-service")
public class AnomaliesService implements AnomaliesIface {

    private static final Logger log = LoggerFactory.getLogger(AnomaliesService.class);

    public AnomaliesService() {
    }

    @Override
    public Flux<Object> detectAnomaly(AnomalyTypes type) {
        Flux<Object> flux;
        try {
            flux = switch (type) {
                case ANOMALY_DETECTION -> DetectUtil.detectAnomaly();
                case ANOMALY_REMOVAL -> RemoveUtil.removeAnomaly();
                case ANOMALY_REPAIR -> RemediateUtil.repairAnomaly();
                case ANOMALY_REPORTING -> ReportUtil.reportAnomaly();
            };
        } catch (Exception anomaliesServiceExc) {
            log.error("{}: Error from AnomaliesService performing anomaly detection:", type, anomaliesServiceExc);
            return Flux.empty();
        } finally {
            log.info("{}: AnomaliesService anomaly detection completed:", type);
        }
        return flux;
    }
}
