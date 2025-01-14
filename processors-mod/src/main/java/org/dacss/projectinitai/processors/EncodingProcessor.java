package org.dacss.projectinitai.processors;

import org.dacss.projectinitai.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>{@link EncodingProcessor}</h1>
 */
@Component
public class EncodingProcessor implements StringProcessingAdviserIface {

    private final Map<String, Integer> categoryMap = new HashMap<>();
    private final Map<Integer, String> reverseMap = new HashMap<>();

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
     * @param encodedValue the encoded value to be decoded
     * @return the original text
     */
    public String decode(int encodedValue) {
        return reverseMap.getOrDefault(encodedValue, "");
    }
}
