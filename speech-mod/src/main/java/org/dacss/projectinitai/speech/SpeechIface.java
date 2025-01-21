package org.dacss.projectinitai.speech;

/**
 * <h1>{@link SpeechIface}</h1>
 */
@FunctionalInterface
public interface SpeechIface {
    /**
     * <h2>{@link #recognizeSpeech()}</h2>
     * Perform speech recognition on the data.
     */
    void recognizeSpeech();
}
