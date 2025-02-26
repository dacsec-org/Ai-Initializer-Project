package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link Encoder}</h1>
 * Encoder for text tokens.
 */
@Service
public class Encoder {

    private static HuggingFaceTokenizer tokenizer;

    /**
     * <h3>{@link #Encoder()}</h3>
     */
    public Encoder() {
        tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    /**
     * <h3>{@link #encode(String)}</h3>
     * Encodes text into token IDs.
     *
     * @param text Text to encode.
     * @return int[] of token IDs.
     */
    public static long[] encode(String text) {
        return tokenizer.encode(text).getIds();
    }
}
