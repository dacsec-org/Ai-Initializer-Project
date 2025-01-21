package org.dacss.projectinitai.sequence;

/**
 * <h1>{@link SequenceIface}</h1>
 */
@FunctionalInterface
public interface SequenceIface {
    /**
     * <h2>{@link #modelSequence()}</h2>
     * Perform sequence modeling on the data.
     */
    void modelSequence();
}
