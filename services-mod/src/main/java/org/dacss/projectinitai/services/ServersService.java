package org.dacss.projectinitai.services;

import org.dacss.projectinitai.annotations.Bridge;
import org.dacss.projectinitai.servers.ServerActions;
import org.dacss.projectinitai.servers.ServerTypes;
import org.dacss.projectinitai.servers.ServersIface;
import org.dacss.projectinitai.servers.utillities.RestartServersUtil;
import org.dacss.projectinitai.servers.UnixSocketServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ServersService}</h1>
 * Backend hilla endpoint service for server operations.
 */
@Service
@Bridge("servers-service")
public class ServersService implements ServersIface {

    private static final Logger log = LoggerFactory.getLogger(ServersService.class);

    /**
     * <h3>{@link #ServersService()}</h3>
     */
    public ServersService() {

    }

    /**
     * <h3>{@link #manageServer(ServerActions, ServerTypes)}</h3>
     * Perform server management operations.
     *
     * @param action The action to perform on the servers.
     * @param type The type of server to manage.
     */
    @Override
    public Flux<Object> manageServer(ServerActions action, ServerTypes type) {
        try {
            return switch (action) {
                case START -> UnixSocketServer.startServer();
                case STOP -> UnixSocketServer.stopServer();
                case RESTART -> RestartServersUtil.restartServer();
                case PING -> UnixSocketServer.pingServer();
            };
        } catch (Exception serversServiceExc) {
            log.error("{}:", serversServiceExc.getMessage(), serversServiceExc);
            return Flux.error(serversServiceExc);
        } finally {
            log.info("{}: {}", action, type);
        }
    }
}
