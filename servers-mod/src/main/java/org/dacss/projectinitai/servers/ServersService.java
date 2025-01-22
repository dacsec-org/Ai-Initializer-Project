package org.dacss.projectinitai.servers;

import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.servers.utillities.*;
import org.dacss.projectinitai.loaders.LoadKernel;
import org.dacss.projectinitai.loaders.UnLoadKernel;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ServersService}</h1>
 * Backend hilla endpoint service for server operations.
 */
@Service
@BrowserCallable
public class ServersService implements ServersIface {

    private final LoadKernel loadKernel;
    private final UnLoadKernel unLoadKernel;

    /**
     * <h2>{@link #ServersService()}</h2>
     * 0-arg constructor to instantiate the {@link LoadKernel} and {@link UnLoadKernel}.
     */
    public ServersService() {
        this.loadKernel = new LoadKernel();
        this.unLoadKernel = new UnLoadKernel();
    }

    /**
     * <h2>{@link #manageServer(String)}</h2>
     * Perform server management operations.
     *
     * @param operation The operation to perform on the servers.
     */
    @Override
    public void manageServer(String operation) {
        switch (operation.toUpperCase()) {
            case "START":
                StartUnixSocketServerUtil.startServer();
                break;
            case "STOP":
                StopUnixServerUtil.stopServer();
                break;
            case "RESTART":
                RestartServersUtil.restartServer();
                break;
            case "PING":
                PingServerUtil.pingServers();
                break;
            case "STOP_HTTP":
                StopHttpServerUtil.stopServer();
                break;
            default:
                throw new IllegalArgumentException(STR."Unknown operation: \{operation}");
        }
    }
}
