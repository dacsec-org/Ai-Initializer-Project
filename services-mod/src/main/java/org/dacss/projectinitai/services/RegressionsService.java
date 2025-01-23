package org.dacss.projectinitai.regressions;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link RegressionsService}</h1>
 * Backend hilla endpoint service for regression operations.
 */
@Service
@BrowserCallable
public class RegressionsService {

    private RegressionsHandler handler;

    /**
     * <h2>{@link #RegressionsService()}</h2>
     * 0-arg constructor to instantiate the {@link RegressionsHandler}.
     */
    public RegressionsService() {
        this.handler = new RegressionsHandler();
    }

    /**
     * <h2>{@link #handleRegressionsAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleRegressionsAction(String action, String data) {
        return switch (RegressionsContexts.valueOf(action.toUpperCase())) {
            case LINEAR_REGRESSION -> handler.handleLinearRegression(data);
            case LOGISTIC_REGRESSION -> handler.handleLogisticRegression(data);
            case POLYNOMIAL_REGRESSION -> handler.handlePolynomialRegression(data);
            case RIDGE_REGRESSION -> handler.handleRidgeRegression(data);
            case LASSO_REGRESSION -> handler.handleLassoRegression(data);
        };
    }
}
