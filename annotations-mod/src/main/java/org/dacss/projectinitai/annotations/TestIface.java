package org.dacss.projectinitai.annotations;

import reactor.core.publisher.Flux;

/**
 * mok test vik
 */
@FunctionalInterface
public interface TestIface {

    Flux<Object> processTestOptions(TestOptions options);
}
