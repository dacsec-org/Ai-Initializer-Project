package org.dacss.projectinitai.anomalies;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link AnomaliesService}</h1>
 * Backend hilla endpoint service for detecting anomalies.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class AnomaliesService {

    private AnomaliesHandler handler;

    /**
     * <h2>{@link #AnomaliesService()}</h2>
     * 0-arg constructor to instantiate the {@link AnomaliesHandler}.
     */
    public AnomaliesService() {
        this.handler = new AnomaliesHandler();
    }

    /**
     * <h2>{@link #handleAnomalyAction(String, String)}</h2>
     *
     * @param action The action to be performed.
     * @param data   The data to be analyzed.
     * @return The result of the action.
     */
    public Object handleAnomalyAction(String action, String data) {
        return switch (AnomaliesContexts.valueOf(action.toUpperCase())) {
            case ANOMALY_DETECTION -> handler.detectAnomaly(data);
            case ANOMALY_REMOVAL -> handler.removeAnomaly(data);
            case ANOMALY_REPAIR -> handler.repairAnomaly(data);
            case ANOMALY_REPORTING -> handler.reportAnomaly(data);
        };
    }
}
