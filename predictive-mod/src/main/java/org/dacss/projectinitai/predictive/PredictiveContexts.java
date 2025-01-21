package org.dacss.projectinitai.predictive;

/**
 * <h1>{@link PredictiveContexts}</h1>
 * Enum class representing the different types of Predictive Analytics techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Predictive Analytics technique.
 */
public enum PredictiveContexts {
    TIME_SERIES_FORECASTING,
    ANOMALY_DETECTION,
    PREDICTIVE_MAINTENANCE;

    public String getContextMessage() {
        return switch (this) {
            case TIME_SERIES_FORECASTING -> """
                    Your purpose is to predict future values based on past data.
                    Use historical data to forecast future trends.
                    """;
            case ANOMALY_DETECTION -> """
                    Your purpose is to identify unusual patterns in data.
                    Detect anomalies that deviate from the norm.
                    """;
            case PREDICTIVE_MAINTENANCE -> """
                    Your purpose is to predict equipment failures before they occur.
                    Use data to anticipate maintenance needs and prevent downtime.
                    """;
        };
    }
}
