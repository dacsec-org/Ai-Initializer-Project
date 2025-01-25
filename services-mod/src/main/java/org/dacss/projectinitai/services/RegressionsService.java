package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.regressions.RegressionsIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link RegressionsService}</h1>
 * Backend hilla endpoint service for regression operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class RegressionsService implements RegressionsIface {


    private static final Logger log = LoggerFactory.getLogger(RegressionsService.class);

    /**
     * <h2>{@link #RegressionsService()}</h2>
     */
    public RegressionsService() {
    }

    /**
     * <h2>{@link #regress()}</h2>
     * Perform regression on the data.
     */
    @Override
    public void regress() {

    }
}

//    /**
//     * <h2>{@link #handleRegressionsAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be processed.
//     * @return The result of the action.
//     */
//    public Object handleRegressionsAction(String action, String data) {
//        return switch (RegressionsContexts.valueOf(action.toUpperCase())) {
//            case LINEAR_REGRESSION -> handler.handleLinearRegression(data);
//            case LOGISTIC_REGRESSION -> handler.handleLogisticRegression(data);
//            case POLYNOMIAL_REGRESSION -> handler.handlePolynomialRegression(data);
//            case RIDGE_REGRESSION -> handler.handleRidgeRegression(data);
//            case LASSO_REGRESSION -> handler.handleLassoRegression(data);
//        };
//    }
//}
