package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.interfaces.ContextType;

/**
 * <h1>{@link ContextualAdviserIface}</h1>
 * Interface for Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
public interface ContextualAdviserIface<T> {
    T updateContext(T userRequest, T aiResponse);
    String getComputerVisionContext(ContextType contextType);
    String getRoboticsContext(ContextType contextType);
}
