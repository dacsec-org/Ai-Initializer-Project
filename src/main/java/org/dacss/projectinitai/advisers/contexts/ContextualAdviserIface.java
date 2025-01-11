package org.dacss.projectinitai.advisers.contexts;

import org.dacss.projectinitai.vision.ComputerVision;
import org.dacss.projectinitai.robotics.Robotics;

public interface ContextualAdviserIface<T> {
    T updateContext(T userRequest, T aiResponse);
    String getComputerVisionContext(ComputerVision computerVision);
    String getRoboticsContext(Robotics robotics);
}
