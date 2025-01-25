package org.dacss.projectinitai.advisers.interfaces;



/**
 * <h1>{@link ContextualAdviserIface}</h1>
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
