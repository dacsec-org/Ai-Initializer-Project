package org.dacss.projectinitai.sequence;

/**
 * <h1>{@link SequenceContexts}</h1>
 * Enum class representing the different types of Sequence Modeling techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Sequence Modeling technique.
 */
public enum SequenceContexts {
    TIME_SERIES_FORECASTING,
    NATURAL_LANGUAGE_PROCESSING,
    GENOMIC_SEQUENCE_ANALYSIS,
    VIDEO_SEQUENCE_ANALYSIS,
    SPEECH_RECOGNITION;

    public String getContextMessage() {
        return switch (this) {
            case TIME_SERIES_FORECASTING -> """
                    Your purpose is to predict future values based on past time series data.
                    Use techniques to analyze and forecast time-dependent data.
                    """;
            case NATURAL_LANGUAGE_PROCESSING -> """
                    Your purpose is to process and understand human language.
                    Use techniques to analyze and generate natural language text.
                    """;
            case GENOMIC_SEQUENCE_ANALYSIS -> """
                    Your purpose is to analyze genomic sequences.
                    Use techniques to identify patterns and variations in DNA sequences.
                    """;
            case VIDEO_SEQUENCE_ANALYSIS -> """
                    Your purpose is to analyze sequences of video frames.
                    Use techniques to extract information and patterns from video data.
                    """;
            case SPEECH_RECOGNITION -> """
                    Your purpose is to recognize and transcribe spoken language.
                    Use techniques to convert speech into text.
                    """;
        };
    }
}
