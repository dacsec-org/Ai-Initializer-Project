package org.dacss.projectinitai.advisers.interfaces;

import org.dacss.projectinitai.contexts.generative.Generative;
import org.dacss.projectinitai.contexts.optimization.Optimization;

/**
 * <h1>{@link AIOutputContextualAdviserIface}</h1>
 * Interface for AI Output Contextual Advisers.
 * <ul>
 *     <li>{@link Generative}</li>
 *     <li>{@link Optimization}</li>
 * </ul>
 */
public interface AIOutputContextualAdviserIface<T> {
    T processAIOutput(T aiResponse);
    String getGenerativeContext(Generative generative);
    String getOptimizationContext(Optimization optimization);
}
