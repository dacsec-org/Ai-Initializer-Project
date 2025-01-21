package org.dacss.projectinitai.reinforcement;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ReinforcementService}</h1>
 * Backend hilla endpoint service for reinforcement learning operations.
 */
@Service
@BrowserCallable
public class ReinforcementService {

    private ReinforcementHandler handler;

    /**
     * <h2>{@link #ReinforcementService()}</h2>
     * 0-arg constructor to instantiate the {@link ReinforcementHandler}.
     */
    public ReinforcementService() {
        this.handler = new ReinforcementHandler();
    }

    /**
     * <h2>{@link #handleReinforcementAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleReinforcementAction(String action, String data) {
        return switch (ReinforcementContexts.valueOf(action.toUpperCase())) {
            case AUTONOMOUS_DRIVING -> handler.handleAutonomousDriving(data);
            case GAME_PLAYING -> handler.handleGamePlaying(data);
            case ROBOTICS_CONTROL -> handler.handleRoboticsControl(data);
            case SIMULATION_ENVIRONMENTS -> handler.handleSimulationEnvironments(data);
            case STRATEGY_OPTIMIZATION -> handler.handleStrategyOptimization(data);
            case RESOURCE_MANAGEMENT -> handler.handleResourceManagement(data);
        };
    }
}
