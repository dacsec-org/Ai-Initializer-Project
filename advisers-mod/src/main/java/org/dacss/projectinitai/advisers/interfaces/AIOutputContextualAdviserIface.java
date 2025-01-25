package org.dacss.projectinitai.advisers.interfaces;
/**/
/**/

/**
 * <h1>{@link AIOutputContextualAdviserIface}</h1>
 * Interface for AI Output Contextual Advisers.
 */
@FunctionalInterface
public interface AIOutputContextualAdviserIface<T> {

    /**
     * {@link #processAIOutput(T)}
     *
     * @param aiResponse
     * @return T - processed AI response
     */
    T processAIOutput(T aiResponse);
}
