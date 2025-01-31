package org.dacss.projectinitai.databases;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link DataBaseIface}</h1>
 */
@FunctionalInterface
public interface DataBaseIface {

    Flux<Object>performDatabaseAction(DataBaseTypes type);
}
