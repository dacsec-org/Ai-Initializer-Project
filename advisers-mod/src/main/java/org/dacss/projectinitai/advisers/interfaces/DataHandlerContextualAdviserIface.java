package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.enums.types.ContextType;

/**
 * <h1>{@link DataHandlerContextualAdviserIface}</h1>
 * Interface for Data Handling Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
public interface DataHandlerContextualAdviserIface<T> {

    /**
     * {@link #getKnowledgeRepresentationReasoningContext(ContextType)}
     *
     * @param contextType
     * @return String - knowledge representation reasoning context
     */
    String getKnowledgeRepresentationReasoningContext(ContextType contextType);

    /**
     * {@link #getPredictiveAnalyticsContext(ContextType)}
     *
     * @param contextType
     * @return String - predictive analytics context
     */
    String getPredictiveAnalyticsContext(ContextType contextType);

    /**
     * {@link #handleData(T)}
     *
     * @param data
     * @return T - handled data
     */
    T handleData(T data);
}
