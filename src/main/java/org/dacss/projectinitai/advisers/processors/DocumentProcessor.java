package org.dacss.projectinitai.advisers.processors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>{@link DocumentProcessor}</h1>
 * Adviser for Document Processing and File IO.
 */
public class DocumentProcessor implements ProcessingAdviserIface<String> {

    @Override
    public String process(String inputOutput) {
        return inputOutput;
    }

    /**
     * Reads the content of a file into a string.
     * @param filePath the path of the file to be read.
     * @return the string containing the file content.
     * @throws IOException if an I/O error occurs.
     */
    public String readFileToString(String filePath) throws IOException {
        return new String(Files.readAllBytes(new File(filePath).toPath()));
    }

    /**
     * Writes a string to a file.
     * @param data the string to be written.
     * @param filePath the path of the file to be written.
     * @throws IOException if an I/O error occurs.
     */
    public void writeStringToFile(String data, String filePath) throws IOException {
        Files.write(new File(filePath).toPath(), data.getBytes());
    }

    /**
     * {@link #getFileInputOutputLocation(String)}
     * @param filePath the file path to be checked.
     * @return the file input/output location.
     */
    public String getFileInputOutputLocation(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
