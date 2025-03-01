package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Decoder {

    private static HuggingFaceTokenizer BBU_DECODER;

    public Decoder() {
        BBU_DECODER = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    public static Mono<String> decode(Flux<Integer> tokenIdsFlux) {
        return tokenIdsFlux.collectList().map(tokenIds -> {
            long[] longTokenIds = tokenIds.stream().mapToLong(Integer::longValue).toArray();
            return BBU_DECODER.decode(longTokenIds);
        });
    }
}