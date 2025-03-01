package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class Encoder {

    private static HuggingFaceTokenizer BBU_ENCODER;

    public Encoder() {
        BBU_ENCODER = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    public static Mono<long[]> encode(Mono<String> textMono) {
        return textMono.map(text -> BBU_ENCODER.encode(text).getIds());
    }
}