package org.dacss.projectinitai.tokenizers;

import reactor.core.publisher.Flux;

@FunctionalInterface
public interface TokenizersIface {

    Flux<Object> tokenize(TokenizeAction action);
}
