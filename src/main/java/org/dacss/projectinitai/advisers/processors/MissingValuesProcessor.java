package org.dacss.projectinitai.advisers.processors;

/**
 * <h1>{@link MissingValuesProcessor}</h1>
 * Processor to handle missing values.
 */
public class MissingValuesProcessor implements ProcessingAdviserIface<String> {

    /**
     * {@link #process(String)}
     * @param text user-input, and ai-output to be processed.
     */
    @Override
    public String process(String text) {
        return text == null || text.isEmpty() ? "N/A" : text;
    }
}
