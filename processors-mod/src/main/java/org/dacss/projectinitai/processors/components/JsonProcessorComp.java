package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link JsonProcessorComp}</h1>
 * JSON processor component class.
 */
@Component
public class JsonProcessorComp implements StringProcessingAdviserIface {

    private static final Logger log = LoggerFactory.getLogger(JsonProcessorComp.class);
    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    /**
     * {@link #processString(String)}
     * Process JSON string.
     *
     * @param stringInputOutput JSON string to process.
     * @return JSON string processed.
     * @throws JsonProcessingException JSON processing exception.
     */
    @Override
    public String processString(String stringInputOutput) throws JsonProcessingException {
        try {
            Object jsonObject = new ObjectMapper().readValue(stringInputOutput, Object.class);
            String prettyJson = objectWriter.writeValueAsString(jsonObject);
            log.info("JSON processing completed");
            return prettyJson;
        } catch (JsonProcessingException processJsonExc) {
            log.error("Error processing JSON string: ", processJsonExc);
            throw processJsonExc;
        }
    }
}
