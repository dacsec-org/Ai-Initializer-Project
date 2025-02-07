package org.dacss.projectinitai.services;
/**/
import org.dacss.projectinitai.anomalies.AnomaliesIface;
import org.dacss.projectinitai.anomalies.AnomalyTypes;
import org.dacss.projectinitai.anomalies.utillities.*;
/**/
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
/**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link AnomaliesService}</h1>
 * Backend hilla endpoint service for detecting anomalies.
 */
@Service
@BrowserCallable
@AnonymousAllowed
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
