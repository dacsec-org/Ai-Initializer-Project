package org.dacss.projectinitai.advisers.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * <h1>{@link JsonProcessor}</h1>
 * processor to handle JSON.
 */
public class JsonProcessor implements ProcessingAdviserIface<String> {

    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    /**
     * {@link #process(String)}
     * @param inputOutput user-input, and ai-output to be processed.
     */
    @Override
    public String process(String inputOutput) {
        try {
            Object jsonObject = new ObjectMapper().readValue(inputOutput, Object.class);
            return objectWriter.writeValueAsString(jsonObject);
        } catch (Exception e) {
            return inputOutput;
        }
    }
}
