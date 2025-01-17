package org.dacss.projectinitai.processors.components;
/**/

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link VectorizationProcessorComp}</h1>
 * Processor for vectorization of JSON responses.
 */
@Component
public class VectorizationProcessorComp {

    private static final Logger log = LoggerFactory.getLogger(VectorizationProcessorComp.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public double[] processJson(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String concatenatedValues = extractValues(jsonNode);

        } catch (Exception e) {
            log.error("Error processing JSON string: ", e);
            return new double[0];
        }
        return new double[0];
    }

    private String extractValues(JsonNode jsonNode) {
        StringBuilder concatenatedValues = new StringBuilder();
        if (jsonNode.isObject()) {
            jsonNode.fields().forEachRemaining(field -> concatenatedValues.append(extractValues(field.getValue())));
        } else if (jsonNode.isArray()) {
            jsonNode.forEach(node -> concatenatedValues.append(extractValues(node)));
        } else {
            concatenatedValues.append(jsonNode.asText());
        }
        return concatenatedValues.toString();
    }
}
