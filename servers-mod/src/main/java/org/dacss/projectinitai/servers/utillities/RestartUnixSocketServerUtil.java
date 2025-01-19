package org.dacss.projectinitai.servers.utillities;
/**/

/**
 * <h1>{@link RestartUnixSocketServerUtil}</h1>
 * Utility class for restarting the Unix Socket server.
 */
public final class RestartUnixSocketServerUtil {

    private RestartUnixSocketServerUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * <h1>{@link #restartServer()}</h1>
     * Restarts the Unix Socket server.
     */
    public static void restartServer() {
        StopUnixServerUtil.stopServer();
        StartUnixSocketServerUtil.startServer();
    }
}
