package org.dacss.projectinitai.processors;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>{@link DocumentProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 *     <li>{@link #readFileToString()}</li>
 *     <li>{@link #writeStringToFile()}</li>
 *     <li>{@link #getFileInputOutputLocation()}</li>
 * </ul>
 */
class DocumentProcessorTest {

    @Test
    void processString() {
        DocumentProcessor processor = new DocumentProcessor();
        String input = "Test document content";
        assertEquals(input, processor.processString(input));
    }

    @Test
    void readFileToString() throws IOException {
        DocumentProcessor processor = new DocumentProcessor();
        String filePath = "test.txt";
        File file = new File(filePath);
        file.createNewFile();
        processor.writeStringToFile("Test content", filePath);
        String result = processor.readFileToString(filePath);
        assertEquals("Test content", result);
        file.delete();
    }

    @Test
    void writeStringToFile() throws IOException {
        DocumentProcessor processor = new DocumentProcessor();
        String filePath = "test.txt";
        processor.writeStringToFile("Test content", filePath);
        File file = new File(filePath);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void getFileInputOutputLocation() {
        DocumentProcessor processor = new DocumentProcessor();
        String filePath = "test.txt";
        String result = processor.getFileInputOutputLocation(filePath);
        assertNotNull(result);
        assertTrue(result.contains(filePath));
    }
}
