package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link DeTokenizer}</h1>
 * DeTokenizer for text tokens.
 */
@Service
public class DeTokenizer {

    private static HuggingFaceTokenizer tokenizer;

    /**
     * <h3>{@link #DeTokenizer()}</h3>
     */
    public DeTokenizer() {
        tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
    }

    /**
     * <h3>{@link #detokenize(String[])}</h3>
     * Detokenizes tokens into text.
     * @param tokens Tokens to detokenize.
     * @return String of detokenized text.
     */
    public static String detokenize(String[] tokens) {
        return tokenizer.decode(tokenizer.encode(tokens).getIds());
    }
}
