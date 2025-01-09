package org.dacss.projectinitai.recomondation;

import lombok.Getter;

/**
 * <h1>{@link RecommendationSystems}</h1>
 */
@Getter
public enum RecommendationSystems {

    COLLABORATIVE_FILTERING,
    CONTENT_BASED_FILTERING,
    HYBRID_RECOMMENDATION_SYSTEMS,
    KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS,
    DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS;

    public String getContextMessage() {
        return switch (this) {
            case COLLABORATIVE_FILTERING -> "Collaborative Filtering recommends items based on user interactions.";
            case CONTENT_BASED_FILTERING -> "Content-Based Filtering recommends items based on item features.";
            case HYBRID_RECOMMENDATION_SYSTEMS -> "Hybrid Recommendation Systems combine multiple recommendation techniques.";
            case KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS -> "Knowledge-Based Recommendation Systems use domain knowledge.";
            case DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS -> "Demographic-Based Recommendation Systems use demographic information.";
        };
    }
}
