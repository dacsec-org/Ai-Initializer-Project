package org.dacss.projectinitai.tokenizers;

/**
 * <h1>{@link TokenizeAction}</h1>
 */
public enum TokenizeAction {
    TOKENIZATION,
    DETOKENIZATION,
    ENCODING,
    DECODING,
    PRE_PROCESSING,
    POST_PROCESSING,
    TRAINING;

    private String text;
    private String[] tokens;
    private int[] tokenIds;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTokens() {
        return tokens;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public int[] getTokenIds() {
        return tokenIds;
    }

    public void setTokenIds(int[] tokenIds) {
        this.tokenIds = tokenIds;
    }
}
