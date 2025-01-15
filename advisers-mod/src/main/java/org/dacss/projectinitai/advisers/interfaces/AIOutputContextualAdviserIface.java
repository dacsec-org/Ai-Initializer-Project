package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.interfaces.ContextType;

/**
 * <h1>{@link AIOutputContextualAdviserIface}</h1>
 * Interface for AI Output Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
public interface AIOutputContextualAdviserIface<T> {
    T processAIOutput(T aiResponse);
    String getGenerativeContext(ContextType contextType);
    String getOptimizationContext(ContextType contextType);
}
