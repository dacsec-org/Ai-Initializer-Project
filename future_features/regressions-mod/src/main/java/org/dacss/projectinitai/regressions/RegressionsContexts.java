package org.dacss.projectinitai.regressions;

/**
 * <h1>{@link RegressionsContexts}</h1>
 * Enum class representing the different types of Regression techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Regression technique.
 */
public enum RegressionsContexts {
    LINEAR_REGRESSION,
    LOGISTIC_REGRESSION,
    POLYNOMIAL_REGRESSION,
    RIDGE_REGRESSION,
    LASSO_REGRESSION;

    public String getContextMessage() {
        return switch (this) {
            case LINEAR_REGRESSION -> """
                    Your purpose is to model the relationship between a dependent variable and one or more independent variables using a linear approach.
                    """;
            case LOGISTIC_REGRESSION -> """
                    Your purpose is to model the probability of a binary outcome based on one or more predictor variables.
                    """;
            case POLYNOMIAL_REGRESSION -> """
                    Your purpose is to model the relationship between a dependent variable and one or more independent variables using a polynomial approach.
                    """;
            case RIDGE_REGRESSION -> """
                    Your purpose is to perform linear regression with L2 regularization to prevent overfitting.
                    """;
            case LASSO_REGRESSION -> """
                    Your purpose is to perform linear regression with L1 regularization to enforce sparsity.
                    """;
        };
    }
}
