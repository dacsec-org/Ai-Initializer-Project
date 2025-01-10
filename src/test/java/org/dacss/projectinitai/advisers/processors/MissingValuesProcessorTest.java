package org.dacss.projectinitai.advisers.processors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>{@link MissingValuesProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class MissingValuesProcessorTest {

    @Test
    void processString() {
        MissingValuesProcessor processor = new MissingValuesProcessor();
        assertEquals("N/A", processor.processString(""));
        assertEquals("N/A", processor.processString(null));
        assertEquals("data", processor.processString("data"));
    }
}
