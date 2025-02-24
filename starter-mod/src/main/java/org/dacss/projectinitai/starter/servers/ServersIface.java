package org.dacss.projectinitai.starter.servers;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link ServersIface}</h1>
 */
@FunctionalInterface
public interface ServersIface {

    Flux<Object> manageServer(ServerActions action, ServerTypes type);
}
