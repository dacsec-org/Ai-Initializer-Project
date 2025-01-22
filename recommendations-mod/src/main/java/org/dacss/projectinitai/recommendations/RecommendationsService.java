package org.dacss.projectinitai.recommendations;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link RecommendationsService}</h1>
 * Backend hilla endpoint service for recommendation operations.
 */
@Service
@BrowserCallable
public class RecommendationsService {

    private RecommendationsHandler handler;

    /**
     * <h2>{@link #RecommendationsService()}</h2>
     * 0-arg constructor to instantiate the {@link RecommendationsHandler}.
     */
    public RecommendationsService() {
        this.handler = new RecommendationsHandler();
    }

    /**
     * <h2>{@link #handleRecommendationsAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleRecommendationsAction(String action, String data) {
        return switch (RecommendationsContexts.valueOf(action.toUpperCase())) {
            case COLLABORATIVE_FILTERING -> handler.handleCollaborativeFiltering(data);
            case CONTENT_BASED_FILTERING -> handler.handleContentBasedFiltering(data);
            case HYBRID_RECOMMENDATION_SYSTEMS -> handler.handleHybridRecommendationSystems(data);
            case KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS -> handler.handleKnowledgeBasedRecommendationSystems(data);
            case DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS -> handler.handleDemographicBasedRecommendationSystems(data);
        };
    }
}
