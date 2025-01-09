package org.dacss.projectinitai.robotics;

import lombok.Getter;

/**
 * <h1>{@link Robotics}</h1>
 */
@Getter
public enum Robotics {

    MOTION_CONTROL,
    OBJECT_MANIPULATION,
    PATH_PLANNING,
    SENSOR_INTEGRATION,
    AUTONOMOUS_NAVIGATION,
    HUMAN_ROBOT_INTERACTION;

    public String getContextMessage() {
        return switch (this) {
            case MOTION_CONTROL -> "Motion Control involves controlling the movement of robots.";
            case OBJECT_MANIPULATION -> "Object Manipulation involves robots handling objects.";
            case PATH_PLANNING -> "Path Planning involves determining the optimal path for robots.";
            case SENSOR_INTEGRATION -> "Sensor Integration involves integrating sensors into robots.";
            case AUTONOMOUS_NAVIGATION -> "Autonomous Navigation involves robots navigating without human intervention.";
            case HUMAN_ROBOT_INTERACTION -> "Human-Robot Interaction involves interactions between humans and robots.";
        };
    }
}
