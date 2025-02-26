package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link Tokenizer}</h1>
 * Tokenizer for text.
 */
@Service
public class Tokenizer {

    private static HuggingFaceTokenizer tokenizer;

    /**
     * <h3>{@link #Tokenizer()}</h3>
     */
    public Tokenizer() {
        tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    /**
     * <h3>{@link #tokenize(String)}</h3>
     * Tokenizes text.
     * @param text Text to tokenize.
     * @return Flux<Object> of tokens.
     */
    public static Flux<Object> tokenize(String text) {
        return Flux.fromArray(tokenizer.encode(text).getTokens()).cast(Object.class);
    }
}
