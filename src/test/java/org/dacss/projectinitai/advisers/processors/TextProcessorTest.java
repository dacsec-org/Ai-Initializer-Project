package org.dacss.projectinitai.advisers.processors;

import org.junit.jupiter.api.Test;

/**
 * <h1>{@link TextProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class TextProcessorTest {

    @Test
    void processString() {
        TextProcessor processor = new TextProcessor();
        String input = "This is a test. Don't remove punctuation!";
        String expected = "this test do not remove punctuation";
        assertEquals(expected, processor.processString(input));
    }
}
