package org.dacss.projectinitai.recomondation;

import lombok.Getter;

@Getter
public enum RecommendationSystems {

    COLLABORATIVE_FILTERING,
    CONTENT_BASED_FILTERING,
    HYBRID_RECOMMENDATION_SYSTEMS,
    KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS,
    DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS;
    String value;

    RecommendationSystems() {}
}
