package org.dacss.projectinitai.tar;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link TarIface}</h1>
 */
@FunctionalInterface
public interface TarIface {

    Flux<Object> processTar(TarActions action);
}
