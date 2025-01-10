package org.dacss.projectinitai.advisers.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link JsonProcessor}</h1>
 */
@Slf4j
@Component
public class JsonProcessor implements StringProcessingAdviserIface {

    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Override
    public String processString(String stringInputOutput) {
        try {
            Object jsonObject = new ObjectMapper().readValue(stringInputOutput, Object.class);
            return objectWriter.writeValueAsString(jsonObject);
        } catch (Exception e) {
            log.error("Error processing JSON: ", e);
            return stringInputOutput;
        }
    }
}
