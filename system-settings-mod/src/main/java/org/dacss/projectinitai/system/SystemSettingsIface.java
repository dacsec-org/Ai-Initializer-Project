package org.dacss.projectinitai.system;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link SystemSettingsIface}</h1>
 * Functional interface to process system settings requests.
 */
@FunctionalInterface
public interface SystemSettingsIface {

    Flux<Object> processSettings(SystemSettingsOptions options);
}
