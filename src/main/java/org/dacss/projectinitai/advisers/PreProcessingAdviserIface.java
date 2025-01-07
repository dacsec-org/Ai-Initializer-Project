package org.dacss.projectinitai.advisers;

/**
 * <h1>{@link PreProcessingAdviserIface}</h1>
 * Interface for Pre-Processing Advisers.
 */
@FunctionalInterface
public interface PreProcessingAdviserIface<T> {

    T preProcess(T userRequest);
}
