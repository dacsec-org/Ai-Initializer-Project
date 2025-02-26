package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link Decoder}</h1>
 * Decoder for token IDs.
 */
@Service
public class Decoder {

    private static HuggingFaceTokenizer tokenizer;

    /**
     * <h3>{@link #Decoder()}</h3>
     */
    public Decoder() {
        tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    /**
     * <h3>{@link #decode(int[])}</h3>
     * Decodes token IDs into text.
     * @param tokenIds Token IDs to decode.
     * @return String of decoded text.
     */
    public static String decode(int[] tokenIds) {
        long[] longTokenIds = new long[tokenIds.length];
        for (int i = 0; i < tokenIds.length; i++) {
            longTokenIds[i] = tokenIds[i];
        }
        return tokenizer.decode(longTokenIds);
    }
}
