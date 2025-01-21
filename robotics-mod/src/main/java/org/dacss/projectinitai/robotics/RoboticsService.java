package org.dacss.projectinitai.robotics;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link RoboticsService}</h1>
 * Backend hilla endpoint service for robotics operations.
 */
@Service
@BrowserCallable
public class RoboticsService {

    private RoboticsHandler handler;

    /**
     * <h2>{@link #RoboticsService()}</h2>
     * 0-arg constructor to instantiate the {@link RoboticsHandler}.
     */
    public RoboticsService() {
        this.handler = new RoboticsHandler();
    }

    /**
     * <h2>{@link #handleRoboticsAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleRoboticsAction(String action, String data) {
        return switch (RoboticsContexts.valueOf(action.toUpperCase())) {
            case MOTION_CONTROL -> handler.handleMotionControl(data);
            case OBJECT_MANIPULATION -> handler.handleObjectManipulation(data);
            case PATH_PLANNING -> handler.handlePathPlanning(data);
            case SENSOR_INTEGRATION -> handler.handleSensorIntegration(data);
            case AUTONOMOUS_NAVIGATION -> handler.handleAutonomousNavigation(data);
            case HUMAN_ROBOT_INTERACTION -> handler.handleHumanRobotInteraction(data);
        };
    }
}
