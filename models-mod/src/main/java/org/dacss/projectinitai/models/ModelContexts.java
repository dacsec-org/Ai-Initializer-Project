package org.dacss.projectinitai.models;

/**
 * <h1>{@link ModelContexts}</h1>
 * Enum class representing the different types of model operations.
 * Each enum constant has a context message that provides a brief description of the purpose of the model operation.
 */
public enum ModelContexts {
    MERGE,
    DESTROY,
    CREATE;

    public String getContextMessage() {
        return switch (this) {
            case MERGE -> """
                    Your purpose is to merge two or more models.
                    Focus on combining the models into a single cohesive unit.
                    """;
            case DESTROY -> """
                    Your purpose is to destroy a model.
                    Focus on safely deleting the model from the system.
                    """;
            case CREATE -> """
                    Your purpose is to create a new model.
                    Focus on initializing and setting up the model.
                    """;
        };
    }
}
