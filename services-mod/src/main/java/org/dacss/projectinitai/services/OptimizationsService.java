package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.optimizations.OptimizationsIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.dacss.projectinitai.optimizations.utillities.GeneticAlgorithmsUtil.geneticAlgorithms;
import static org.dacss.projectinitai.optimizations.utillities.IntegerProgramingUtil.integerProgramming;
import static org.dacss.projectinitai.optimizations.utillities.LinearProgramingUtil.linearProgramming;

/**
 * <h1>{@link OptimizationsService}</h1>
 * Backend hilla endpoint service for optimization operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class OptimizationsService implements OptimizationsIface {

    private static final Logger log = LoggerFactory.getLogger(OptimizationsService.class);

    /**
     * <h2>{@link #OptimizationsService()}</h2>
     */
    public OptimizationsService() {
    }

    /**
     * <h2>{@link #optimize(String, String)}</h2>
     * Perform optimization on the data.
     */
    @Override
    public void optimize(String action, String data) {
        try {
            switch (action) {
                case "linear_programming":
                    linearProgramming();
                    break;
                case "integer_programming":
                    integerProgramming();
                    break;
                case "genetic_algorithms":
                    geneticAlgorithms();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid optimization action: " + action);
            }
        } catch (Exception optimizationServiceExc) {
            log.error("Error occurred while performing optimization: {}", optimizationServiceExc.getMessage());
            throw new RuntimeException("Error occurred while performing optimization: " + optimizationServiceExc.getMessage());
        }
    }
}
