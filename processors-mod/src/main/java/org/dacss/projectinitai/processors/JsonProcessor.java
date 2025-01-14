package org.dacss.projectinitai.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link JsonProcessor}</h1>
 * JSON processor.
 */
@Slf4j
@Component
public class JsonProcessor implements StringProcessingAdviserIface {

    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Override
    public String processString(String stringInputOutput) throws JsonProcessingException {
        try {
            Object jsonObject = new ObjectMapper().readValue(stringInputOutput, Object.class);
            String prettyJson = objectWriter.writeValueAsString(jsonObject);
            log.info("JSON processing completed");
            return prettyJson;
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON string: ", e);
            throw e;
        }
    }
}
