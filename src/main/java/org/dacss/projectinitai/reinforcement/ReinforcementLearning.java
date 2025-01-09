package org.dacss.projectinitai.reinforcement;

import lombok.Getter;

/**
 * <h1>{@link ReinforcementLearning}</h1>
 */
@Getter
public enum ReinforcementLearning {

    AUTONOMOUS_DRIVING,
    GAME_PLAYING,
    ROBOTICS_CONTROL,
    SIMULATION_ENVIRONMENTS,
    STRATEGY_OPTIMIZATION,
    RESOURCE_MANAGEMENT;

    public String getContextMessage() {
        return switch (this) {
            case AUTONOMOUS_DRIVING -> "Autonomous Driving involves self-driving vehicles.";
            case GAME_PLAYING -> "Game Playing uses AI to play and master games.";
            case ROBOTICS_CONTROL -> "Robotics Control involves controlling robots to perform tasks.";
            case SIMULATION_ENVIRONMENTS -> "Simulation Environments simulate real-world scenarios for training.";
            case STRATEGY_OPTIMIZATION -> "Strategy Optimization optimizes decision-making strategies.";
            case RESOURCE_MANAGEMENT -> "Resource Management optimizes the allocation of resources.";
        };
    }
}
