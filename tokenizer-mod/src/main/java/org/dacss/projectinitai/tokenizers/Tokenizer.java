package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Tokenizer {

    private HuggingFaceTokenizer tokenizer;

    public Tokenizer() {
        this.tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    public Mono<Flux<Object>> tokenize(Mono<String> textMono) {
        return textMono.map(text -> {
            String[] tokens = tokenizer.encode(text).getTokens();
            return Flux.fromArray(tokens).cast(Object.class);
        });
    }
}