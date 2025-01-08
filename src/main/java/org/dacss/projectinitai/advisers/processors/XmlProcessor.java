package org.dacss.projectinitai.advisers.processors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * <h1>{@link XmlProcessor}</h1>
 * Processor for XML responses.
 */
public class XmlProcessor implements ProcessingAdviserIface<String> {

    /**
     * {@link #process(String)}
     * @param aiResponse user-input, and ai-output to be processed.
     * @return formatted XML response.
     */
    @Override
    public String process(String aiResponse) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(aiResponse)));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            return writer.toString();
        } catch (Exception e) {
            return aiResponse; // Return original response if parsing fails
        }
    }
}
