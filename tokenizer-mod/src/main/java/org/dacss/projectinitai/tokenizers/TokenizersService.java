package org.dacss.projectinitai.tokenizers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link TokenizersService}</h1>
 * Tokenizers service, handles tokenization and detokenization for LLM's that require it.
 * as of now were not calling this module from the frontend, so we don't add the service
 * to the serves-mod,or the <code>@Bridge</code> annotation.
 */
@Service
public class TokenizersService implements TokenizersIface {

    private static final Logger log = LoggerFactory.getLogger(TokenizersService.class);

    public TokenizersService() {}

    @Override
    public Flux<Object> tokenize(TokenizeAction action) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case TOKENIZATION -> Tokenizer.tokenize(action.getText());
                case DETOKENIZATION -> Flux.just(DeTokenizer.detokenize(action.getTokens()));
                case ENCODING -> Flux.just(Encoder.encode(action.getText()));
                case DECODING -> Flux.just(Decoder.decode(action.getTokenIds()));
                default -> Flux.empty();
            };
        } catch (Exception tokenizersServiceExc) {
            log.error("{}: Error from TokenizersService performing action:", action, tokenizersServiceExc);
            return Flux.empty();
        } finally {
            log.info("TokenizersService action completed: {}", action);
        }
        return flux;
    }
}
