package org.dacss.projectinitai.advisers;

/**
 * <h1>{@link AIOutputContextualAdviserIface}</h1>
 * Interface for AI Output Contextual Advisers.
 */
@FunctionalInterface
public interface AIOutputContextualAdviserIface<T> {
    T processAIOutput(T aiResponse);
}
