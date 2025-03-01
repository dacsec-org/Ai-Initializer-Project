package org.dacss.projectinitai.clients;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UniversalLLMClientIface {

    Mono<String> prompt(String message);
}
