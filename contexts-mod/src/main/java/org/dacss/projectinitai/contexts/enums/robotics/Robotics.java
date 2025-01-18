package org.dacss.projectinitai.contexts.enums.robotics;

/**
 * <h1>{@link Robotics}</h1>
 * Enum class representing the different types of Robotics techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Robotics technique.
 */
public enum Robotics {

    MOTION_CONTROL,
    OBJECT_MANIPULATION,
    PATH_PLANNING,
    SENSOR_INTEGRATION,
    AUTONOMOUS_NAVIGATION,
    HUMAN_ROBOT_INTERACTION;

    public String getContextMessage() {
        return switch (this) {
            case MOTION_CONTROL -> "Your purpose is to control the movement of robots. Use motion control techniques to manage and direct robotic movements.";
            case OBJECT_MANIPULATION -> "Your purpose is to handle objects with robots. Use object manipulation techniques to grasp, move, and interact with objects.";
            case PATH_PLANNING -> "Your purpose is to determine the optimal path for robots. Use path planning algorithms to navigate and reach destinations efficiently.";
            case SENSOR_INTEGRATION -> "Your purpose is to integrate sensors into robots. Use sensor integration methods to enhance robotic perception and interaction.";
            case AUTONOMOUS_NAVIGATION -> "Your purpose is to navigate robots without human intervention. Use autonomous navigation systems to enable self-directed movement.";
            case HUMAN_ROBOT_INTERACTION -> "Your purpose is to facilitate interactions between humans and robots. Use human-robot interaction techniques to improve communication and collaboration.";
        };
    }
}
