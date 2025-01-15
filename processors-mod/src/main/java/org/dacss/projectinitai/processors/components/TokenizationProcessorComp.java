package org.dacss.projectinitai.processors.components;

import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>{@link TokenizationProcessorComp}</h1>
 */
@Component
public class TokenizationProcessorComp implements StringProcessingAdviserIface {

    @Override
    public String processString(String stringInputOutput) {
        List<String> tokens = Arrays.stream(stringInputOutput.split("\\s+"))
                                    .collect(Collectors.toList());
        return String.join(" ", tokens);
    }
}
