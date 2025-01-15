package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.contexts.predictive.PredictiveAnalytics;

/**
 * <h1>{@link DataHandlerContextualAdviserIface}</h1>
 * Interface for Data Handling Contextual Advisers.
 * <ul>
 *     <li>{@link KnowledgeRepresentationReasoning}</li>
 *     <li>{@link PredictiveAnalytics}</li>
 * </ul>
 */
public interface DataHandlerContextualAdviserIface<T> {
    String getKnowledgeRepresentationReasoningContext(KnowledgeRepresentationReasoning krr);
    String getPredictiveAnalyticsContext(PredictiveAnalytics predictiveAnalytics);
    T handleData(T data);
}
