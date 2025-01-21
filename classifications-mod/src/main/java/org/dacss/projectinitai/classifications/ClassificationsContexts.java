package org.dacss.projectinitai.classifications;

/**
 * <h1>{@link ClassificationsContexts}</h1>
 * Context messages for different classification models.
 */
public enum ClassificationsContexts {
    LOGISTIC_REGRESSION,
    DECISION_TREE,
    RANDOM_FOREST,
    SVM,
    NAIVE_BAYES,
    NEURAL_NETWORK;

    public String getContextMessage() {
        return switch (this) {
            case LOGISTIC_REGRESSION -> """
                    Your purpose is to perform classification using the Logistic Regression algorithm.
                    Focus on modeling the probability of a binary outcome.
                    """;
            case DECISION_TREE -> """
                    Your purpose is to perform classification using the Decision Tree algorithm.
                    Emphasize creating a model that predicts the value of a target variable by learning simple decision rules.
                    """;
            case RANDOM_FOREST -> """
                    Your purpose is to perform classification using the Random Forest algorithm.
                    Focus on creating an ensemble of decision trees to improve predictive accuracy and control over-fitting.
                    """;
            case SVM -> """
                    Your purpose is to perform classification using the Support Vector Machine (SVM) algorithm.
                    Emphasize finding the hyperplane that best separates the classes in the feature space.
                    """;
            case NAIVE_BAYES -> """
                    Your purpose is to perform classification using the Naive Bayes algorithm.
                    Focus on applying Bayes' theorem with strong (naive) independence assumptions between the features.
                    """;
            case NEURAL_NETWORK -> """
                    Your purpose is to perform classification using a Neural Network.
                    Emphasize learning complex patterns and relationships in the data through multiple layers of interconnected nodes.
                    """;
        };
    }
}
