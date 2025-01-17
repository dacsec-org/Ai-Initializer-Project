package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import org.springframework.stereotype.Component;

/**
 * <h1>{@link MissingValuesProcessorComp}</h1>
 * Missing values processor component class.
 */
@Component
public class MissingValuesProcessorComp implements StringProcessingAdviserIface {

    /**
     * Process missing values.
     *
     * @param stringInputOutput String to process.
     * @return String processed.
     */
    @Override
    public String processString(String stringInputOutput) {
        return stringInputOutput == null || stringInputOutput.isEmpty() ? "N/A" : stringInputOutput;
    }
}
