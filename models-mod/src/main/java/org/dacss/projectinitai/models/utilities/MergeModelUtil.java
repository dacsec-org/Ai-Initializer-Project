package org.dacss.projectinitai.models.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <h1>{@link MergeModelUtil}</h1>
 * Utility class for merging models.
 */
public class MergeModelUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Flux<Object> mergeModels(String modelPath1, String modelPath2) throws IOException {
        //todo: add validation for model paths
        JsonNode model1 = objectMapper.readTree(new File(modelPath1));
        JsonNode model2 = objectMapper.readTree(new File(modelPath2));
        JsonNode mergedModel = mergeJsonNodes(model1, model2);
        return Flux.just(mergedModel);
    }

    public static Flux<Object> mergeModels(List<String> modelPaths) throws IOException {
        JsonNode mergedModel = objectMapper.createObjectNode();
        for (String modelPath : modelPaths) {
            JsonNode model = objectMapper.readTree(new File(modelPath));
            mergedModel = mergeJsonNodes(mergedModel, model);
        }
        return Flux.just(mergedModel);
    }

    private static JsonNode mergeJsonNodes(JsonNode node1, JsonNode node2) {
        if (node1.isObject() && node2.isObject()) {
            ObjectNode merged = (ObjectNode) node1;
            Iterator<Map.Entry<String, JsonNode>> fields = node2.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                JsonNode value = field.getValue();
                if (merged.has(field.getKey())) {
                    value = mergeJsonNodes(merged.get(field.getKey()), value);
                }
                merged.set(field.getKey(), value);
            }
            return merged;
        }
        return node2;
    }
}
