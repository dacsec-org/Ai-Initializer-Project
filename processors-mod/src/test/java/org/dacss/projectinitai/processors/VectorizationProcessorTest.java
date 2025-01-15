package org.dacss.projectinitai.processors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link VectorizationProcessorTest}
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class VectorizationProcessorTest {

    @Test
    void processString() {
        VectorizationProcessor processor = new VectorizationProcessor();
        String input = "test";
        String result = processor.processString(input);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
