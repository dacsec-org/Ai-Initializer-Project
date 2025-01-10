package org.dacss.projectinitai.advisers.processors;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>{@link TokenizationProcessor}</h1>
 */
@Component
public class TokenizationProcessor implements StringProcessingAdviserIface {

    @Override
    public String processString(String stringInputOutput) {
        List<String> tokens = Arrays.stream(stringInputOutput.split("\\s+"))
                                    .collect(Collectors.toList());
        return String.join(" ", tokens);
    }
}
