package org.dacss.projectinitai.advisers.processors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>{@link HtmlProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class HtmlProcessorTest {

    @Test
    void processString() {
        HtmlProcessor processor = new HtmlProcessor();
        String input = "<html><body><p>Test</p></body></html>";
        String expected = "Test";
        assertEquals(expected, processor.processString(input));
    }
}
