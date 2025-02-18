package org.dacss.projectinitai.servers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link ServersIface}</h1>
 */
@FunctionalInterface
public interface ServersIface {

    Flux<Object> manageServer(ServerActions action, ServerTypes type);
}
