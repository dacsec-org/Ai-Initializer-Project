package org.dacss.projectinitai.starter.servers.utillities;

import org.dacss.projectinitai.starter.servers.UnixSocketServer;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link RestartServersUtil}</h1>
 * Utility class for restarting the Unix Socket server.
 */
public final class RestartServersUtil {

    /**
     * <h3>{@link #RestartServersUtil()}</h3>
     * Private constructor to prevent instantiation of this utility class.
     */
    private RestartServersUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * <h3>{@link #restartServer()}</h3>
     * Restarts the Unix Socket server.
     *
     * @return A {@link Flux} of {@link Object} that represents the restart of the Unix Socket server.
     */
    public static Flux<Object> restartServer() {
        return Flux.concat(UnixSocketServer.stopServer(), UnixSocketServer.startServer());
    }
}
