package org.dacss.projectinitai.servers.utillities;
/**/

import org.springframework.stereotype.Component;

/**
 * <h1>{@link RestartServersUtil}</h1>
 * Utility class for restarting the Unix Socket, and or http server.
 */
@Component
public final class RestartServersUtil {

    private RestartServersUtil() {
        throw new UnsupportedOperationException("Utility class");
    }


    public static void restartServer() {
        StopUnixServerUtil.stopServer();
        StartUnixSocketServerUtil.startServer();
    }
}
