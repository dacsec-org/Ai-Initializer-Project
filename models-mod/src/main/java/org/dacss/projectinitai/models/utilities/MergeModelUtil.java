package org.dacss.projectinitai.models.utilities;
/**/
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <h1>{@link MergeModelUtil}</h1>
 * <p>
 * Utility class for merging models.
 * </p>
 */
public class MergeModelUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Merges two models.
     *
     * @param modelPath1 The path of the first model.
     * @param modelPath2 The path of the second model.
     * @return The merged model as a JsonNode.
     * @throws IOException If an I/O error occurs.
     */
    public static JsonNode mergeModels(String modelPath1, String modelPath2) throws IOException {
        JsonNode model1 = objectMapper.readTree(new File(modelPath1));
        JsonNode model2 = objectMapper.readTree(new File(modelPath2));
        return mergeJsonNodes(model1, model2);
    }

    /**
     * Merges multiple models.
     *
     * @param modelPaths The list of model paths.
     * @return The merged model as a JsonNode.
     * @throws IOException If an I/O error occurs.
     */
    public static JsonNode mergeModels(List<String> modelPaths) throws IOException {
        JsonNode mergedModel = objectMapper.createObjectNode();
        for (String modelPath : modelPaths) {
            JsonNode model = objectMapper.readTree(new File(modelPath));
            mergedModel = mergeJsonNodes(mergedModel, model);
        }
        return mergedModel;
    }

    /**
     * Merges two JsonNodes.
     *
     * @param node1 The first JsonNode.
     * @param node2 The second JsonNode.
     * @return The merged JsonNode.
     */
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
