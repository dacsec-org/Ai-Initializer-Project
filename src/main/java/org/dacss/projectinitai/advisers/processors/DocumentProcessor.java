package org.dacss.projectinitai.advisers.processors;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>{@link DocumentProcessor}</h1>
 */
@Component
public class DocumentProcessor implements StringProcessingAdviserIface {

    @Override
    public String processString(String stringInputOutput) {
        return stringInputOutput;
    }

    public String readFileToString(String filePath) throws IOException {
        return new String(Files.readAllBytes(new File(filePath).toPath()));
    }

    public void writeStringToFile(String data, String filePath) throws IOException {
        Files.write(new File(filePath).toPath(), data.getBytes());
    }

    public String getFileInputOutputLocation(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
