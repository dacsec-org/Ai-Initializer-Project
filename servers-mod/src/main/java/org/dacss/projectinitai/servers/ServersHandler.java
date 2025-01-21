package org.dacss.projectinitai.servers;

import org.springframework.stereotype.Component;
import org.dacss.projectinitai.servers.utillities.*;

/**
 * <h1>{@link ServersHandler}</h1>
 * Handler class for server operations.
 */
@Component
public class ServersHandler implements ServersIface {

    private final ServersService serversService;

    /**
     * <h2>{@link #ServersHandler()}</h2>
     * 0-arg constructor to instantiate the {@link ServersService}.
     */
    public ServersHandler() {
        this.serversService = new ServersService();
    }

    public String handleStart() {
        StartUnixSocketServerUtil.startServer();
        return "Server started successfully";
    }

    public String handleStop() {
        StopUnixServerUtil.stopServer();
        return "Server stopped successfully";
    }

    public String handleRestart() {
        RestartServersUtil.restartServer();
        return "Server restarted successfully";
    }

    public String handlePing() {
        PingServerUtil.pingServers();
        return "Server pinged successfully";
    }

    public String handleStopHttp() {
        StopHttpServerUtil.stopServer();
        return "HTTP server stopped successfully";
    }

    /**
     * <h2>{@link ServersIface#manage()}</h2>
     * Perform server management operations.
     */
    @Override
    public void manage() {
        //todo: implement
    }
}
