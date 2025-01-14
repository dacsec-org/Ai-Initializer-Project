package org.dacss.projectinitai.processors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VectorizationProcessor {

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
