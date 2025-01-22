package org.dacss.projectinitai.advisers.interfaces;



/**
 * <h1>{@link DataHandlerContextualAdviserIface}</h1>
 * Interface for Data Handling Contextual Advisers.
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
