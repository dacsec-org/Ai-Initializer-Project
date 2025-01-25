package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;

import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.anomalies.AnomaliesIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link AnomaliesService}</h1>
 * Backend hilla endpoint service for detecting anomalies.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class AnomaliesService implements AnomaliesIface {


    private static final Logger log = LoggerFactory.getLogger(AnomaliesService.class);

    /**
     * <h2>{@link #AnomaliesService()}</h2>
     */
    public AnomaliesService() {
    }

    /**
     * <h2>{@link AnomaliesIface#detectAnomaly()}</h2>
     * detect anomaly in the data.
     */
    @Override
    public void detectAnomaly() {

    }
}

//    /**
//     * <h2>{@link #handleAnomalyAction(String, String)}</h2>
//     *
//     * @param action The action to be performed.
//     * @param data   The data to be analyzed.
//     * @return The result of the action.
//     */
//    public Object handleAnomalyAction(String action, String data) {
//        return switch (AnomaliesContexts.valueOf(action.toUpperCase())) {
//            case ANOMALY_DETECTION -> handler.detectAnomaly(data);
//            case ANOMALY_REMOVAL -> handler.removeAnomaly(data);
//            case ANOMALY_REPAIR -> handler.repairAnomaly(data);
//            case ANOMALY_REPORTING -> handler.reportAnomaly(data);
//        };
//    }
