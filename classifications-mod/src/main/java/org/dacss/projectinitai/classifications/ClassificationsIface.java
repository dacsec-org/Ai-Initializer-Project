package org.dacss.projectinitai.classifications;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link ClassificationsIface}</h1>
 */
@FunctionalInterface
public interface ClassificationsIface {

    Flux<Object> classify(ClassificationsTypes type);
}
