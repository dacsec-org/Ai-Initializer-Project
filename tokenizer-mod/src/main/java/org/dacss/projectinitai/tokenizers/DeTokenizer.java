package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DeTokenizer {

    private static HuggingFaceTokenizer tokenizer;

    public DeTokenizer() {
        tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    public static Mono<String> detokenize(Flux<String> tokensFlux) {
        return tokensFlux.collectList().map(tokens -> tokenizer.decode(tokenizer.encode(tokens.toArray(new String[0])).getIds()));
    }
}