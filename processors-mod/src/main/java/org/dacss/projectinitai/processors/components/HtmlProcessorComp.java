package org.dacss.projectinitai.processors.components;
/**/

import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link HtmlProcessorComp}</h1>
 * This class is used to process HTML content.
 */
@Component
public class HtmlProcessorComp implements StringProcessingAdviserIface {

    /**
     * {@link #processString(String)}
     * Process string data.
     *
     * @param stringInputOutput
     * @return the cleaned HTML content
     */
    @Override
    public String processString(String stringInputOutput) {
        return Jsoup.clean(stringInputOutput, Safelist.none());
    }
}
