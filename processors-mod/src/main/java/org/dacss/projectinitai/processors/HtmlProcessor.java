package org.dacss.projectinitai.processors;

import org.dacss.projectinitai.interfaces.StringProcessingAdviserIface;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link HtmlProcessor}</h1>
 * This class is used to process HTML content.
 */
@Component
public class HtmlProcessor implements StringProcessingAdviserIface {

    /**
     * @param stringInputOutput 
     * @return
     */
    @Override
    public String processString(String stringInputOutput) {
        return Jsoup.clean(stringInputOutput, Safelist.none());
    }
}
