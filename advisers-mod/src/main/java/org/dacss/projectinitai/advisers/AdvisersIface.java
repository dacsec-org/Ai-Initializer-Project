package org.dacss.projectinitai.advisers;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link AdvisersIface}</h1>
 */
@FunctionalInterface
public interface AdvisersIface {

    Flux<Object> advise(AdviseAction action);
}
