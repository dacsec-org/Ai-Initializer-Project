package org.dacss.projectinitai.servers;

import org.dacss.projectinitai.servers.utillities.PingServerUtil;
import org.dacss.projectinitai.servers.utillities.RestartUnixSocketServerUtil;
import org.dacss.projectinitai.servers.utillities.StartUnixSocketServerUtil;
import org.dacss.projectinitai.servers.utillities.StopUnixServerUtil;
import org.dacss.projectinitai.servers.utillities.StopHttpServerUtil;

/**
 * <h1>{@link ServersHandler}</h1>
 * <p>
 *     This class is used to handle the server actions.
 * </p>
 */
public class ServersHandler {

    /**
     * <h1>{@link #startServer()}</h1>
     * Starts the server.
     */
    public void startServer() {
        StartUnixSocketServerUtil.startServer();
    }

    /**
     * <h1>{@link #stopServer()}</h1>
     * Stops the server.
     */
    public void stopServer() {
        StopUnixServerUtil.stopServer();
    }

    /**
     * <h1>{@link #restartServer()}</h1>
     * Restarts the server.
     */
    public void restartServer() {
        RestartUnixSocketServerUtil.restartServer();
    }

    /**
     * <h1>{@link #pingServers()}</h1>
     * Pings the servers.
     */
    public void pingServers() {
        PingServerUtil.pingServers();
    }

    /**
     * <h1>{@link #stopHttpServer()}</h1>
     * Stops the HTTP server.
     */
    public void stopHttpServer() {
        StopHttpServerUtil.stopServer();
    }
}
