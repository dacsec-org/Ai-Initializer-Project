package org.dacss.projectinitai.anomalies;

/**
 * <h1>{@link AnomaliesContexts}</h1>
 * Context messages for different types of anomalies, and how to handle them.
 */
public enum AnomaliesContexts {
    ANOMALY_DETECTION,
    ANOMALY_REMOVAL,
    ANOMALY_REPAIR,
    ANOMALY_REPORTING;

    public String getContextMessage() {
        return switch (this) {
            case ANOMALY_DETECTION -> """
                    Your purpose is to detect anomalies in the data.
                    To perform this task, analyze the provided data for any irregularities or deviations from the norm.
                    Use statistical methods, machine learning models, or predefined rules to identify potential anomalies.
                    """;
            case ANOMALY_REMOVAL -> """
                    Your purpose is to remove anomalies from the data.
                    To perform this task, identify the detected anomalies and apply appropriate techniques to eliminate them.
                    This may involve data cleaning, filtering, or correction methods to ensure the data is accurate and consistent.
                    """;
            case ANOMALY_REPAIR -> """
                    Your purpose is to repair anomalies in the data.
                    To perform this task, identify the detected anomalies and apply appropriate techniques to correct them.
                    This may involve imputing missing values, correcting erroneous data points, or using interpolation methods to restore data integrity.
                    """;
            case ANOMALY_REPORTING -> """
                    Your purpose is to report anomalies in the data.
                    To perform this task, generate detailed reports on the detected anomalies, including their nature, location, and potential impact.
                    Use visualization tools, summary statistics, and descriptive analysis to present the findings clearly and comprehensively.
                    """;
        };
    }
}
