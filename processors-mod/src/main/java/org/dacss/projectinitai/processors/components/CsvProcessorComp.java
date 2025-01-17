package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <h1>{@link CsvProcessorComp}</h1>
 * CSV Processor Component.
 */
@Component
public class CsvProcessorComp implements StringProcessingAdviserIface {

    /**
     * {@link #processString(String)}
     * Process string data.
     *
     * @param stringInputOutput
     * @return
     */
    @Override
    public String processString(String stringInputOutput) {
        return Arrays.stream(stringInputOutput.split("\n"))
                .map(line -> line.trim().replaceAll("\\s+", ","))
                .collect(Collectors.joining("\n"));
    }
}
