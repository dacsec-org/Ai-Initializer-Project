package org.dacss.projectinitai.advisers;

import org.dacss.projectinitai.advisers.components.PostProcessingAdviserComponent;

/**
 * <h1>{@link PostProcessingAdviserIface}</h1>
 * Interface for Post-Processing Advisers.
 * @see PostProcessingAdviserComponent
 */
@FunctionalInterface
public interface PostProcessingAdviserIface<T> {

    T postProcess(T aiResponse);
}
