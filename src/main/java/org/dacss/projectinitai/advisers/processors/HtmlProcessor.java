package org.dacss.projectinitai.advisers.processors;

import org.dacss.projectinitai.advisers.processors.ProcessingAdviserIface;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * <h1>{@link HtmlProcessor}</h1>
 * This class is used to process HTML content.
 */
public class HtmlProcessor implements ProcessingAdviserIface<String> {


    /**
     * {@link #process(String)}
     * @param inputOutput user-input, and ai-output to be processed.
     */
    @Override
    public String process(String inputOutput) {
        return Jsoup.clean(inputOutput, Safelist.none());
    }
}
