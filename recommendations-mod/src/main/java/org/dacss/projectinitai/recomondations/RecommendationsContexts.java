package org.dacss.projectinitai.recomondations;

/**
 * <h1>{@link RecommendationsContexts}</h1>
 * Enum class representing the different types of Recommendation Systems.
 * Each enum constant has a context message that provides a brief description of the purpose of the Recommendation System technique.
 */
public enum RecommendationsContexts {
    COLLABORATIVE_FILTERING,
    CONTENT_BASED_FILTERING,
    HYBRID_RECOMMENDATION_SYSTEMS,
    KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS,
    DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS;

    public String getContextMessage() {
        return switch (this) {
            case COLLABORATIVE_FILTERING -> """
                    Your purpose is to recommend items based on user interactions.
                    Use collaborative filtering techniques to find similar users or items.
                    """;
            case CONTENT_BASED_FILTERING -> """
                    Your purpose is to recommend items based on item features.
                    Use content-based filtering techniques to match user preferences with item attributes.
                    """;
            case HYBRID_RECOMMENDATION_SYSTEMS -> """
                    Your purpose is to combine multiple recommendation techniques.
                    Use hybrid recommendation systems to leverage the strengths of different methods.
                    """;
            case KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS -> """
                    Your purpose is to use domain knowledge to recommend items.
                    Use knowledge-based systems to provide recommendations based on specific criteria.
                    """;
            case DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS -> """
                    Your purpose is to use demographic information to recommend items.
                    Use demographic-based systems to tailor recommendations based on user demographics.
                    """;
        };
    }
}
