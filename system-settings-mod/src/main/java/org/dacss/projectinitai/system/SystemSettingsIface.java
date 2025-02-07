package org.dacss.projectinitai.system;

import reactor.core.publisher.Mono;

/**
 * <h1>{@link SystemSettingsIface}</h1>
 * Functional interface to process system settings requests.
 */
@FunctionalInterface
public interface SystemSettingsIface {

    Mono<Object> processSettings(SystemSettingsOptions systemSettingsOptions);
}
