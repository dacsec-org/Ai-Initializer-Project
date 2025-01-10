package org.dacss.projectinitai.advisers.processors;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CsvProcessor implements StringProcessingAdviserIface {

    @Override
    public String processString(String stringInputOutput) {
        return Arrays.stream(stringInputOutput.split("\n"))
                .map(line -> line.trim().replaceAll("\\s+", ","))
                .collect(Collectors.joining("\n"));
    }
}
