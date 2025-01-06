package org.dacss.projectinitai.optimization;

import lombok.Getter;

@Getter
public enum Optimization {

    LINEAR_PROGRAMMING,
    INTEGER_PROGRAMMING,
    GENETIC_ALGORITHMS;
    String value;

    Optimization() {}
}
