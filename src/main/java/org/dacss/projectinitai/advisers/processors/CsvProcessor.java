package org.dacss.projectinitai.advisers.processors;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <h1>{@link CsvProcessor}</h1>
 * Processing Adviser for CSV data.
 */
public class CsvProcessor implements ProcessingAdviserIface<String> {


    /**
     * {@link #process(String)}
     * @param inputOutput user-input, and ai-output to be processed.
     * @return processed user-input, and ai-output.
     */
    @Override
    public String process(String inputOutput) {
        return Arrays.stream(inputOutput.split("\n"))
                .map(line -> line.trim().replaceAll("\\s+", ","))
                .collect(Collectors.joining("\n"));
    }
}
