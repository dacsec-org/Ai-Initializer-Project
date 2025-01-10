package org.dacss.projectinitai.advisers.processors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link PdfProcessor}
 * Methods under test:
 * <ul>
 *     <li>{@link #processString()}</li>
 * </ul>
 */
class PdfProcessorTest {

    @Test
    void processString() {
        PdfProcessor processor = new PdfProcessor();
        String input = "PDF content";
        assertEquals(input, processor.processString(input));
    }
}
