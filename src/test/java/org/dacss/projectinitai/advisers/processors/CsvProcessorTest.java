package org.dacss.projectinitai.advisers.processors;

import org.junit.jupiter.api.Test;

/**
 * <h1>{@link CsvProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class CsvProcessorTest {

    @Test
    void processString() {
        CsvProcessor processor = new CsvProcessor();
        String input = "name age\nJohn 30\nJane 25";
        String expected = "name,age\nJohn,30\nJane,25";
        assertEquals(expected, processor.processString(input));
    }
}
