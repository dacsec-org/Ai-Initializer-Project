package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.vision.ComputerVision;
import org.dacss.projectinitai.contexts.robotics.Robotics;

/**
 * <h1>{@link ContextualAdviserIface}</h1>
 * Interface for Contextual Advisers.
 */
public interface ContextualAdviserIface<T> {
    T updateContext(T userRequest, T aiResponse);
    String getComputerVisionContext(ComputerVision computerVision);
    String getRoboticsContext(Robotics robotics);
}
