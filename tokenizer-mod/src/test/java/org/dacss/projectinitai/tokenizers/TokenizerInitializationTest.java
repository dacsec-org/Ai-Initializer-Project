package org.dacss.projectinitai.tokenizers;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.testng.annotations.Test;

public class TokenizerInitializationTest {

    @Test
    public void testTokenizerInitialization() {
        HuggingFaceTokenizer tokenizer = HuggingFaceTokenizer.newInstance("bert-base-uncased");
        assert tokenizer != null;
    }
}