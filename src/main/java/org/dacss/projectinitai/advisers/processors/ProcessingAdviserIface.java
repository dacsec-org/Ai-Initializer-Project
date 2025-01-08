package org.dacss.projectinitai.advisers.processors;

/**
 * <h1>{@link ProcessingAdviserIface}</h1>
 * Functional Interface for Processing Advisers.
 */
@FunctionalInterface
public interface ProcessingAdviserIface<T> {

    /**
     * {@link #process(T)}
     * @param inputOutput user-input, and ai-output to be processed.
     */
    T process(T inputOutput);
}
