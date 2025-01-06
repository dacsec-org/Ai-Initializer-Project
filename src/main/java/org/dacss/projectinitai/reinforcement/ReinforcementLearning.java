package org.dacss.projectinitai.reinforcement;

import lombok.Getter;

@Getter
public enum ReinforcementLearning {

    AUTONOMOUS_DRIVING,
    GAME_PLAYING,
    ROBOTICS_CONTROL,
    SIMULATION_ENVIRONMENTS,
    STRATEGY_OPTIMIZATION,
    RESOURCE_MANAGEMENT;
    String value;

    ReinforcementLearning() {}
}
