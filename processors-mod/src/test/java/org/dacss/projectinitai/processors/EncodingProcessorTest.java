package org.dacss.projectinitai.processors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>{@link EncodingProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 *     <li>{@link #decode()}</li>
 * </ul>
 */
class EncodingProcessorTest {

    @Test
    void processString() {
        EncodingProcessor processor = new EncodingProcessor();
        String input = "test";
        String result = processor.processString(input);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void decode() {
        EncodingProcessor processor = new EncodingProcessor();
        String input = "test";
        String encoded = processor.processString(input);
        String decoded = processor.decode(Integer.parseInt(encoded));
        assertEquals(input, decoded);
    }
}
