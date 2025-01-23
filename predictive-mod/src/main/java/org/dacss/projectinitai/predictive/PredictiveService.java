package org.dacss.projectinitai.predictive;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link PredictiveService}</h1>
 * Backend hilla endpoint service for predictive analytics operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class PredictiveService {

    private PredictiveHandler handler;

    /**
     * <h2>{@link #PredictiveService()}</h2>
     * 0-arg constructor to instantiate the {@link PredictiveHandler}.
     */
    public PredictiveService() {
        this.handler = new PredictiveHandler();
    }

    /**
     * <h2>{@link #handlePredictiveAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handlePredictiveAction(String action, String data) {
        return switch (PredictiveContexts.valueOf(action.toUpperCase())) {
            case TIME_SERIES_FORECASTING -> handler.handleTimeSeriesForecasting(data);
            case ANOMALY_DETECTION -> handler.handleAnomalyDetection(data);
            case PREDICTIVE_MAINTENANCE -> handler.handlePredictiveMaintenance(data);
        };
    }
}
