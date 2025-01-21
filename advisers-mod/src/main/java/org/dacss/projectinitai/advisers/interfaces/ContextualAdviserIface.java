package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.ContextType;

/**
 * <h1>{@link ContextualAdviserIface}</h1>
 * Interface for Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
public interface ContextualAdviserIface<T> {

    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest
     * @param aiResponse
     * @return T - updated context
     */
    T updateContext(T userRequest, T aiResponse);

    /**
     * {@link #getComputerVisionContext(ContextType)}
     *
     * @param contextType
     * @return String - computer vision context
     */
    String getComputerVisionContext(ContextType contextType);

    /**
     * {@link #getRoboticsContext(ContextType)}
     *
     * @param contextType
     * @return String - robotics context
     */
    String getRoboticsContext(ContextType contextType);
}
