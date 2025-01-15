package org.dacss.projectinitai.processors;

import org.junit.jupiter.api.Test;

/**
 * <h1>{@link TokenizationProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class TokenizationProcessorTest {

    @Test
    void processString() {
        TokenizationProcessor processor = new TokenizationProcessor();
        String input = "This is a test";
        String expected = "This is a test";
        assertEquals(expected, processor.processString(input));
    }
}
