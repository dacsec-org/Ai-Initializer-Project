package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.servers.ServersIface;
import org.dacss.projectinitai.servers.utillities.*;
import org.dacss.projectinitai.loaders.LoadKernel;
import org.dacss.projectinitai.loaders.UnLoadKernel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * <h1>{@link ServersService}</h1>
 * Backend hilla endpoint service for server operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class ServersService implements ServersIface {

    private static final Logger log = LoggerFactory.getLogger(ServersService.class);
//    private final LoadKernel loadKernel;
//    private final UnLoadKernel unLoadKernel;

    /**
     * <h2>{@link #ServersService()}</h2>
     * 0-arg constructor to instantiate the {@link LoadKernel} and {@link UnLoadKernel}.
     */
    public ServersService() {
//        this.loadKernel = new LoadKernel();
//        this.unLoadKernel = new UnLoadKernel();
    }

    /**
     * <h2>{@link #manageServer(String)}</h2>
     * Perform server management operations.
     *
     * @param operation The operation to perform on the servers.
     */
    @Override
    public void manageServer(String operation) {
        switch (operation) {
            case "start":
                StartUnixSocketServerUtil.startServer();
                break;
            case "stop":
                StopUnixServerUtil.stopServer();
                break;
            case "restart":
                RestartServersUtil.restartServer();
                break;
            case "ping":
                PingServerUtil.pingServers();
                break;
            case "stop-http":
                StopHttpServerUtil.stopServer();
                break;
            default:
                log.error("Invalid operation: {}", operation);
        }
    }
}
