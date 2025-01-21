package org.dacss.projectinitai.optimizations;

/**
 * <h1>{@link OptimizationContexts}</h1>
 * Enum class representing the different types of Optimization techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Optimization technique.
 */
public enum OptimizationContexts {
    LINEAR_PROGRAMMING,
    INTEGER_PROGRAMMING,
    GENETIC_ALGORITHMS;

    public String getContextMessage() {
        return switch (this) {
            case LINEAR_PROGRAMMING -> """
                    Your purpose is to optimize a linear objective function.
                    Use linear constraints to find the best outcome.
                    """;
            case INTEGER_PROGRAMMING -> """
                    Your purpose is to optimize a linear objective function with integer variables.
                    Use integer constraints to find the best outcome.
                    """;
            case GENETIC_ALGORITHMS -> """
                    Your purpose is to optimize based on natural selection.
                    Use genetic algorithms to find the best solution through evolution-inspired techniques.
                    """;
        };
    }
}
