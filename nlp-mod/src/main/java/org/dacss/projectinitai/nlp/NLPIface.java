package org.dacss.projectinitai.nlp;

/**
 * <h1>{@link NLPIface}</h1>
 */
@FunctionalInterface
public interface NLPIface {
    /**
     * <h2>{@link #processText(String, String)}</h2>
     * Perform NLP on the data.
     *
     * @param action The action to be performed.
     * @param data The data to be processed.
     */
    void processText(String action, String data);
}
