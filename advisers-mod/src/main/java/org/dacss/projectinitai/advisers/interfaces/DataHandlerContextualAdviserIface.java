package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.ContextType;

/**
 * <h1>{@link DataHandlerContextualAdviserIface}</h1>
 * Interface for Data Handling Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
@FunctionalInterface
public interface DataHandlerContextualAdviserIface<T> {

    /**
     * {@link #handleData(T)}
     *
     * @param data
     * @return T - handled data
     */
    T handleData(T data);
}
