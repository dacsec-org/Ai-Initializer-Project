package org.dacss.projectinitai.processors.components;

import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;


@Component
public class MissingValuesProcessorComp implements StringProcessingAdviserIface {

    @Override
    public String processString(String stringInputOutput) {
        return stringInputOutput == null || stringInputOutput.isEmpty() ? "N/A" : stringInputOutput;
    }
}
