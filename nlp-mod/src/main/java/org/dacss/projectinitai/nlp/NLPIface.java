package org.dacss.projectinitai.nlp;

/**
 * <h1>{@link NLPIface}</h1>
 */
@FunctionalInterface
public interface NLPIface {
    /**
     * <h2>{@link #processText()}</h2>
     * Perform NLP on the data.
     */
    void processText();
}
