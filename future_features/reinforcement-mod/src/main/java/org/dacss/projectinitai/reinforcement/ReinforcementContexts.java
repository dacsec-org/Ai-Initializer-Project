package org.dacss.projectinitai.reinforcement;

/**
 * <h1>{@link ReinforcementContexts}</h1>
 * Enum class representing the different types of Reinforcement Learning techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Reinforcement Learning technique.
 */
public enum ReinforcementContexts {
    AUTONOMOUS_DRIVING,
    GAME_PLAYING,
    ROBOTICS_CONTROL,
    SIMULATION_ENVIRONMENTS,
    STRATEGY_OPTIMIZATION,
    RESOURCE_MANAGEMENT;

    public String getContextMessage() {
        return switch (this) {
            case AUTONOMOUS_DRIVING -> """
                    Your purpose is to enable self-driving vehicles.
                    Use reinforcement learning to navigate and make driving decisions.
                    """;
            case GAME_PLAYING -> """
                    Your purpose is to play and master games.
                    Use reinforcement learning to develop strategies and improve performance.
                    """;
            case ROBOTICS_CONTROL -> """
                    Your purpose is to control robots to perform tasks.
                    Use reinforcement learning to optimize robotic movements and actions.
                    """;
            case SIMULATION_ENVIRONMENTS -> """
                    Your purpose is to simulate real-world scenarios for training.
                    Use reinforcement learning to create and manage simulation environments.
                    """;
            case STRATEGY_OPTIMIZATION -> """
                    Your purpose is to optimize decision-making strategies.
                    Use reinforcement learning to enhance strategic planning and execution.
                    """;
            case RESOURCE_MANAGEMENT -> """
                    Your purpose is to optimize the allocation of resources.
                    Use reinforcement learning to manage and distribute resources efficiently.
                    """;
        };
    }
}
