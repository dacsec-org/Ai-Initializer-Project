package org.dacss.projectinitai.annotations.moks;

import reactor.core.publisher.Flux;

/**
 * mok test vik
 */
@FunctionalInterface
public interface MokIface {

    Flux<Object> processTestOptions(MokOptions options);
}
