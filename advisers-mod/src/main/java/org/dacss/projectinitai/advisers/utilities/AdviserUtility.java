package org.dacss.projectinitai.advisers.utilities;

/**
 * <h1>{@link AdviserUtility}</h1>
 * <p>
 *     This utility class provides common methods for adviser tasks.
 * </p>
 */
public class AdviserUtility {

    /**
     * Validates the input data for adviser actions.
     * @param input The input data to be validated.
     * @return true if the input is valid, false otherwise.
     */
    public static boolean validateInput(String input) {
        // Implement the logic for input validation
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Formats the output data for adviser actions.
     * @param output The output data to be formatted.
     * @return The formatted output data.
     */
    public static String formatOutput(String output) {
        // Implement the logic for output formatting
        return output.trim();
    }

    /**
     * Logs the adviser action.
     * @param action The action to be logged.
     * @param input The input data for the action.
     * @param output The output data for the action.
     */
    public static void logAction(String action, String input, String output) {
        // Implement the logic for logging the action
        System.out.println("Action: " + action + ", Input: " + input + ", Output: " + output);
    }

    /**
     * Converts the input data to uppercase.
     * @param input The input data to be converted.
     * @return The converted input data in uppercase.
     */
    public static String convertToUpperCase(String input) {
        return input.toUpperCase();
    }

    /**
     * Reverses the input data.
     * @param input The input data to be reversed.
     * @return The reversed input data.
     */
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
