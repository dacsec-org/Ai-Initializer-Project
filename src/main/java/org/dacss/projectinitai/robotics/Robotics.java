package org.dacss.projectinitai.robotics;

import lombok.Getter;

@Getter
public enum Robotics {

    MOTION_CONTROL,
    OBJECT_MANIPULATION,
    PATH_PLANNING,
    SENSOR_INTEGRATION,
    AUTONOMOUS_NAVIGATION,
    HUMAN_ROBOT_INTERACTION;
    String value;

    Robotics() {}
}
