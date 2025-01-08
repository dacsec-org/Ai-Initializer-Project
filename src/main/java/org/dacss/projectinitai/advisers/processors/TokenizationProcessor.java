package org.dacss.projectinitai.advisers.processors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>{@link TokenizationProcessor}</h1>
 * Pre-processor to tokenize text data.
 */
public class TokenizationProcessor implements ProcessingAdviserIface<String> {

    @Override
    public String preProcess(String text) {
        List<String> tokens = Arrays.stream(text.split("\\s+"))
                                    .collect(Collectors.toList());
        return String.join(" ", tokens);
    }
}
