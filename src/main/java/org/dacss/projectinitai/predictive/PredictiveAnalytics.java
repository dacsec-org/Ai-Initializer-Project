package org.dacss.projectinitai.predictive;

import lombok.Getter;

/**
 * <h1>{@link PredictiveAnalytics}</h1>
 */
@Getter
public enum PredictiveAnalytics {

    TIME_SERIES_FORECASTING,
    ANOMALY_DETECTION,
    PREDICTIVE_MAINTENANCE;

    public String getContextMessage() {
        return switch (this) {
            case TIME_SERIES_FORECASTING -> "Time Series Forecasting predicts future values based on past data.";
            case ANOMALY_DETECTION -> "Anomaly Detection identifies unusual patterns in data.";
            case PREDICTIVE_MAINTENANCE -> "Predictive Maintenance predicts equipment failures before they occur.";
        };
    }
}
