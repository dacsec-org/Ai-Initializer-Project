package org.dacss.projectinitai.servers;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ServersService}</h1>
 * Backend hilla endpoint service for server operations.
 */
@Service
@BrowserCallable
public class ServersService {

    private ServersHandler handler;

    /**
     * <h2>{@link #ServersService()}</h2>
     * 0-arg constructor to instantiate the {@link ServersHandler}.
     */
    public ServersService() {
        this.handler = new ServersHandler();
    }

    /**
     * <h2>{@link #handleServerAction(String)}</h2>
     * @param action The action to be performed.
     * @return The result of the action.
     */
    public Object handleServerAction(String action) {
        return switch (ServersContexts.valueOf(action.toUpperCase())) {
            case START -> handler.handleStart();
            case STOP -> handler.handleStop();
            case RESTART -> handler.handleRestart();
            case PING -> handler.handlePing();
            case STOP_HTTP -> handler.handleStopHttp();
        };
    }
}
