package org.dacss.projectinitai.predictive;

import lombok.Getter;

@Getter
public enum PredictiveAnalytics {

        TIME_SERIES_FORECASTING,
        ANOMALY_DETECTION,
        PREDICTIVE_MAINTENANCE;
        String value;

        PredictiveAnalytics() {
    }
}
