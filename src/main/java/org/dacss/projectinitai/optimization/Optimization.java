package org.dacss.projectinitai.optimization;

import lombok.Getter;

/**
 * <h1>{@link Optimization}</h1>
 */
@Getter
public enum Optimization {

    LINEAR_PROGRAMMING,
    INTEGER_PROGRAMMING,
    GENETIC_ALGORITHMS;

    public String getContextMessage() {
        return switch (this) {
            case LINEAR_PROGRAMMING -> "Linear Programming optimizes a linear objective function.";
            case INTEGER_PROGRAMMING -> "Integer Programming optimizes a linear objective function with integer variables.";
            case GENETIC_ALGORITHMS -> "Genetic Algorithms are used for optimization based on natural selection.";
        };
    }
}
