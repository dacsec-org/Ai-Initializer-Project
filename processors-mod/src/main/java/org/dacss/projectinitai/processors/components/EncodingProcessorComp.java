package org.dacss.projectinitai.processors.components;
/**/

import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>{@link EncodingProcessorComp}</h1>
 * Encoding Processor Component.
 */
@Component
public class EncodingProcessorComp implements StringProcessingAdviserIface {

    private final Map<String, Integer> categoryMap = new HashMap<>();
    private final Map<Integer, String> reverseMap = new HashMap<>();

    /**
     * {@link #processString(String)}
     * Process string data.
     *
     * @param text the text to be processed
     * @return the encoded value
     */
    @Override
    public String processString(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        int encodedValue = categoryMap.computeIfAbsent(text, k -> {
            int value = categoryMap.size() + 1;
            reverseMap.put(value, k);
            return value;
        });
        return String.valueOf(encodedValue);
    }

    /**
     * {@link #decode(int)}
     *
     * @param encodedValue the encoded value
     * @return String the decoded value
     */
    public String decode(int encodedValue) {
        return reverseMap.getOrDefault(encodedValue, "");
    }
}
