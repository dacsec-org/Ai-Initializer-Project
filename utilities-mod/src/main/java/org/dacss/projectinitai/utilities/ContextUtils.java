package org.dacss.projectinitai.utilities;


/**
 * <h1>{@link ContextUtils}</h1>
 * Utility class for context handling.
 */
public class ContextUtils {

    /**
     * Get context message based on the provided context type.
     *
     * @param contextType {@link ContextType}
     * @return String
     */
    public static String getContextMessage(ContextType contextType) {
        return contextType.getContextMessage();
    }
}
