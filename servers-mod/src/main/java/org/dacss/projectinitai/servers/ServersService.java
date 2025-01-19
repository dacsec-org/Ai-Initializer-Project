package org.dacss.projectinitai.servers;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ServersService}</h1>
 * Backend service for managing servers.
 */
@Service
@BrowserCallable
public class ServersService {

    private final ServersHandler serversHandler;

    /**
     * {@link ServersService}
     * 0-arg constructor.
     */
    public ServersService() {
        this.serversHandler = new ServersHandler();
    }

    /**
     * <h1>{@link #handleServerAction(String)}</h1>
     * Handles the server action.
     *
     * @param action The action to be handled.
     */
    public void handleServerAction(String action) {
        switch (action.toLowerCase()) {
            case "start":
                serversHandler.startServer();
                break;
            case "stop":
                serversHandler.stopServer();
                break;
            case "restart":
                serversHandler.restartServer();
                break;
            case "ping":
                serversHandler.pingServers();
                break;
            case "stophttp":
                serversHandler.stopHttpServer();
                break;
            default:
                throw new IllegalArgumentException(STR."Unknown action: \{action}");
        }
    }
}
