package org.dacss.projectinitai.advisers.processors;

import org.junit.jupiter.api.Test;

/**
 * {@link XmlProcessorTest}
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class XmlProcessorTest {

    @Test
    void processString() {
        XmlProcessor processor = new XmlProcessor();
        String input = "<root><name>John</name></root>";
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<root>\n  <name>John</name>\n</root>\n";
        assertEquals(expected, processor.processString(input));
    }
}
