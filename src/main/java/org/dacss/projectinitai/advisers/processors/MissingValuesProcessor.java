package org.dacss.projectinitai.advisers.processors;

import org.springframework.stereotype.Component;


@Component
public class MissingValuesProcessor implements StringProcessingAdviserIface {

    @Override
    public String processString(String stringInputOutput) {
        return stringInputOutput == null || stringInputOutput.isEmpty() ? "N/A" : stringInputOutput;
    }
}
