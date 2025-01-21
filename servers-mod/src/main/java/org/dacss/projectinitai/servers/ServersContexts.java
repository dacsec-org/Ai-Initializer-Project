package org.dacss.projectinitai.servers;

/**
 * <h1>{@link ServersContexts}</h1>
 * Enum class representing the different types of Server operations.
 * Each enum constant has a context message that provides a brief description of the purpose of the Server operation.
 */
public enum ServersContexts {
    START,
    STOP,
    RESTART,
    PING,
    STOP_HTTP;

    public String getContextMessage() {
        return switch (this) {
            case START -> """
                    Your purpose is to start the server.
                    Use techniques to initialize and run the server.
                    """;
            case STOP -> """
                    Your purpose is to stop the server.
                    Use techniques to safely shut down the server.
                    """;
            case RESTART -> """
                    Your purpose is to restart the server.
                    Use techniques to stop and then start the server again.
                    """;
            case PING -> """
                    Your purpose is to ping the server.
                    Use techniques to check if the server is up and running.
                    """;
            case STOP_HTTP -> """
                    Your purpose is to stop the HTTP server.
                    Use techniques to shut down the HTTP server.
                    """;
        };
    }
}
