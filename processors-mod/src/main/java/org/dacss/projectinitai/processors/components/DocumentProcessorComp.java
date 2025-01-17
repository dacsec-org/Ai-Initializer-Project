package org.dacss.projectinitai.processors.components;
/**/

import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>{@link DocumentProcessorComp}</h1>
 * Document Processor Component.
 */
@Component
public class DocumentProcessorComp implements StringProcessingAdviserIface {

    /**
     * {@link #processString(String)}
     * Process string data.
     *
     * @param stringInputOutput
     * @return stringInputOutput
     */
    @Override
    public String processString(String stringInputOutput) {
        return stringInputOutput;
    }

    /**
     * {@link #readFileToString(String)}
     * Read file to string.
     *
     * @param filePath
     * @return String
     * @throws IOException if file not found
     */
    public String readFileToString(String filePath) throws IOException {
        return new String(Files.readAllBytes(new File(filePath).toPath()));
    }

    /**
     * {@link #writeStringToFile(String, String)}
     * Write string to file.
     *
     * @param data
     * @param filePath
     * @throws IOException if file not found
     */
    public void writeStringToFile(String data, String filePath) throws IOException {
        Files.write(new File(filePath).toPath(), data.getBytes());
    }

    /**
     * {@link #getFileInputOutputLocation(String)}
     * Get file input output location.
     *
     * @param filePath
     * @return String
     */
    public String getFileInputOutputLocation(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
