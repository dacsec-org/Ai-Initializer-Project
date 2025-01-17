package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

import com.vaadin.flow.component.notification.Notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * <h1>{@link XmlProcessorComp}</h1>
 * Processor for XML responses.
 */
@Component
public class XmlProcessorComp implements StringProcessingAdviserIface {


    private static final Logger log = LoggerFactory.getLogger(XmlProcessorComp.class);

    @Override
    public String processString(String stringInputOutput) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(stringInputOutput)));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            return writer.toString();
        } catch (Exception e) {
            log.error("Error processing XML: ", e);
            Notification.show(STR."Error processing XML: \{e.getMessage()}");
            return stringInputOutput; // Return original response if parsing fails
        }
    }
}
