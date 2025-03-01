package org.dacss.projectinitai.tokenizers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TokenizersService implements TokenizersIface {

    private static final Logger log = LoggerFactory.getLogger(TokenizersService.class);
    private final Tokenizer tokenizer;

    public TokenizersService() {
        this.tokenizer = new Tokenizer();
    }

    @Override
    public Flux<Object> tokenize(TokenizeAction action) {
        try {
            return switch (action) {
                case TOKENIZATION -> tokenizer.tokenize(Mono.just("")).flatMapMany(flux -> flux).cast(Object.class);
                case DETOKENIZATION -> DeTokenizer.detokenize(Flux.fromArray(new String[0])).flux().cast(Object.class);
                case ENCODING -> Encoder.encode(Mono.just("")).flux().cast(Object.class);
                case DECODING -> {
                    Integer[] tokenIds = convertToIntegerArray(new int[0]);
                    yield Decoder.decode(Flux.fromArray(tokenIds)).flux().cast(Object.class);
                }
            };
        } catch (Exception tokenizersServiceExc) {
            log.error("{}: Error from TokenizersService performing action:", action, tokenizersServiceExc);
            return Flux.empty();
        } finally {
            log.info("TokenizersService action completed: {}", action);
        }
    }

    private Integer[] convertToIntegerArray(int[] intArray) {
        return java.util.Arrays.stream(intArray).boxed().toArray(Integer[]::new);
    }
}