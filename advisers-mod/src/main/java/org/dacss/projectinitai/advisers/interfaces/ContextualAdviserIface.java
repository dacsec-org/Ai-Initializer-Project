package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.ContextType;

/**
 * <h1>{@link ContextualAdviserIface}</h1>
 * Interface for Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
@FunctionalInterface
public interface ContextualAdviserIface<T> {

    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest
     * @param aiResponse
     * @return T - updated context
     */
    T updateContext(T userRequest, T aiResponse);
}
