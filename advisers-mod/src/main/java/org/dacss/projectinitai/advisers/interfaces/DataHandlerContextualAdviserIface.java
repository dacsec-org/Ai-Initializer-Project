package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.interfaces.ContextType;

/**
 * <h1>{@link DataHandlerContextualAdviserIface}</h1>
 * Interface for Data Handling Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
public interface DataHandlerContextualAdviserIface<T> {
    String getKnowledgeRepresentationReasoningContext(ContextType contextType);
    String getPredictiveAnalyticsContext(ContextType contextType);
    T handleData(T data);
}
