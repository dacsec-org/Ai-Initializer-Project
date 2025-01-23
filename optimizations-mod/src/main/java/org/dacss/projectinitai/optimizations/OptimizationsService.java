package org.dacss.projectinitai.optimizations;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link OptimizationsService}</h1>
 * Backend hilla endpoint service for optimization operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class OptimizationsService {

    private OptimizationsHandler handler;

    /**
     * <h2>{@link #OptimizationsService()}</h2>
     * 0-arg constructor to instantiate the {@link OptimizationsHandler}.
     */
    public OptimizationsService() {
        this.handler = new OptimizationsHandler();
    }

    /**
     * <h2>{@link #handleOptimizationAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleOptimizationAction(String action, String data) {
        return switch (OptimizationContexts.valueOf(action.toUpperCase())) {
            case LINEAR_PROGRAMMING -> handler.handleLinearProgramming(data);
            case INTEGER_PROGRAMMING -> handler.handleIntegerProgramming(data);
            case GENETIC_ALGORITHMS -> handler.handleGeneticAlgorithms(data);
        };
    }
}
