package org.dacss.projectinitai.advisers.interfaces;
/**/
import org.dacss.projectinitai.contexts.ContextType;
/**/

/**
 * <h1>{@link AIOutputContextualAdviserIface}</h1>
 * Interface for AI Output Contextual Advisers.
 * <ul>
 *     <li>{@link ContextType}</li>
 * </ul>
 */
public interface AIOutputContextualAdviserIface<T> {

    /**
     * {@link #processAIOutput(T)}
     *
     * @param aiResponse
     * @return T - processed AI response
     */
    T processAIOutput(T aiResponse);

    /**
     * {@link #getGenerativeContext(ContextType)}
     *
     * @param contextType
     * @return String - context
     */
    String getGenerativeContext(ContextType contextType);

    /**
     * {@link #getOptimizationContext(ContextType)}
     *
     * @param contextType
     * @return String - context
     */
    String getOptimizationContext(ContextType contextType);
}
