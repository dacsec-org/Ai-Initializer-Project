package org.dacss.projectinitai.processors;

import org.junit.jupiter.api.Test;

/**
 * <h1>{@link JsonProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class JsonProcessorTest {

    @Test
    void processString() {
        JsonProcessor processor = new JsonProcessor();
        String input = "{\"name\":\"John\",\"age\":30}";
        String expected = "{\n  \"name\" : \"John\",\n  \"age\" : 30\n}";
        assertEquals(expected, processor.processString(input));
    }
}
