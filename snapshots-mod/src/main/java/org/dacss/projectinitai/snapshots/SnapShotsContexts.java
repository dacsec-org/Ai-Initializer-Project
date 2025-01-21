package org.dacss.projectinitai.snapshots;

/**
 * <h1>{@link SnapShotsContexts}</h1>
 * Enum class representing the different types of SnapShots operations.
 * Each enum constant has a context message that provides a brief description of the purpose of the SnapShots operation.
 */
public enum SnapShotsContexts {
    CREATE,
    LIST,
    DELETE,
    COPY,
    EXECUTE_COMMAND;

    public String getContextMessage() {
        return switch (this) {
            case CREATE -> """
                    Your purpose is to create a snapshot of a directory.
                    Use techniques to capture the current state of the directory.
                    """;
            case LIST -> """
                    Your purpose is to list snapshots in a directory.
                    Use techniques to display all available snapshots.
                    """;
            case DELETE -> """
                    Your purpose is to delete a snapshot.
                    Use techniques to remove the specified snapshot.
                    """;
            case COPY -> """
                    Your purpose is to copy a snapshot.
                    Use techniques to duplicate the specified snapshot.
                    """;
            case EXECUTE_COMMAND -> """
                    Your purpose is to execute a command related to snapshots.
                    Use techniques to run the specified command.
                    """;
        };
    }
}
