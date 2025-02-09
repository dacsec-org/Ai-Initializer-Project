package org.dacss.projectinitai.system;

import java.nio.file.Path;
import java.nio.file.Paths;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link LoggingSettings}</h1>
 * Class provides the functionality to get and set the logging settings.
 */
public class LoggingSettings {

    private static final Path DEFAULT_LOG_DIRECTORY = Paths.get("/var/log/project-ai-initializer");
    private static String logLevel = "INFO"; // Default log level

    /**
     * <h3>{@link #LoggingSettings()}</h3>
     * Private constructor to prevent instantiation.
     */
    private LoggingSettings() {}

    /**
     * <h3>{@link #getLoggingSettings()}</h3>
     * Returns the current logging settings.
     *
     * @return A Mono containing the current logging settings.
     */
    public static Mono<Object> getLoggingSettings() {
        return Mono.just("Log Directory: " + DEFAULT_LOG_DIRECTORY + ", Log Level: " + logLevel);
    }

    /**
     * <h3>{@link #getLogLevel()}</h3>
     * Returns the current logging level.
     *
     * @return The current logging level.
     */
    public static String getLogLevel() {
        return logLevel;
    }

    /**
     * <h3>{@link #setLogLevel(String)}</h3>
     * Sets the logging level.
     *
     * @param level The logging level to set.
     */
    public static void setLogLevel(String level) {
        logLevel = level;
    }
}
/**/
