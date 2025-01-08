package org.dacss.projectinitai.advisers.processors;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>{@link EncodingProcessor}</h1>
 * Processor to encode and decode text data.
 */
public class EncodingProcessor implements ProcessingAdviserIface<String> {

    private final Map<String, Integer> categoryMap = new HashMap<>();
    private final Map<Integer, String> reverseMap = new HashMap<>();

    /**
     * {@link #process(String)}
     * @param text user-input, and ai-output to be processed.
     * @return the encoded value
     */
    @Override
    public String process(String text) {
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
     * @param encodedValue the encoded value to be decoded
     * @return the original text
     */
    public String decode(int encodedValue) {
        return reverseMap.getOrDefault(encodedValue, "");
    }
}
