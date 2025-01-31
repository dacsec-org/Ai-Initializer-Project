package org.dacss.projectinitai.advisers.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link AdviserUtil}</h1>
 * <p>
 *     This utility class provides common methods for adviser tasks.
 * </p>
 */
@Component
public class AdviserUtil {
    //todo: 86 this class after implementing the new utility class

    private static final Logger log = LoggerFactory.getLogger(AdviserUtil.class);

    /**
     * Validates the input data for adviser actions.
     *
     * @param input The input data to be validated.
     * @return true if the input is valid, false otherwise.
     */
    public static boolean validateInput(String input) {
        // Implement the logic for input validation
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Formats the output data for adviser actions.
     *
     * @param output The output data to be formatted.
     * @return The formatted output data.
     */
    public static String formatOutput(String output) {
        // Implement the logic for output formatting
        return output.trim();
    }

    /**
     * Logs the adviser action.
     *
     * @param action The action to be logged.
     * @param input  The input data for the action.
     * @param output The output data for the action.
     */
    public static void logAction(String action, String input, String output) {
        log.info("Action: {}, Input: {}, Output: {}", action, input, output);
    }
}
