package org.dacss.projectinitai.advisers.contexts;

import org.dacss.projectinitai.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.predictive.PredictiveAnalytics;

public interface DataHandlerContextualAdviserIface<T> {
    String getKnowledgeRepresentationReasoningContext(KnowledgeRepresentationReasoning krr);
    String getPredictiveAnalyticsContext(PredictiveAnalytics predictiveAnalytics);
    T handleData(T data);
}
