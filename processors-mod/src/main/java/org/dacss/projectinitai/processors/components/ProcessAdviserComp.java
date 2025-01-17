package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.ByteProcessingAdviserIface;
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import com.fasterxml.jackson.core.JsonProcessingException;
import org.dacss.projectinitai.processors.interfaces.ByteProcessingAdviserIface;
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>{@link ProcessAdviserComp}</h1>
 * Process adviser component class.
 */
@Component
public class ProcessAdviserComp {

    /**
     * thread-local processors map.
     */
    private final ThreadLocal<Map<Class<?>, Object>> processors =
            ThreadLocal.withInitial(HashMap::new);

    /**
     * {@link #ProcessAdviserComp()} 0-arg constructor.
     */
    public ProcessAdviserComp() {
        registerProcessors(
                new TextProcessorComp(),
                new CsvProcessorComp(),
                new JsonProcessorComp(),
                new XmlProcessorComp(),
                new HtmlProcessorComp(),
                new VectorizationProcessorComp(),
                new EncodingProcessorComp(),
                new MissingValuesProcessorComp(),
                new ImageProcessorComp(),
                new AudioProcessorComp(),
                new VideoProcessorComp(),
                new DocumentProcessorComp(),
                new PdfProcessorComp(),
                new TokenizationProcessorComp()
        );
    }

    /**
     * {@link #registerProcessors(Object...)}
     *
     * @param preProcessors Pre-processors.
     */
    void registerProcessors(Object... preProcessors) {
        for (Object preProcessor : preProcessors) {
            processors.get().put(preProcessor.getClass(), preProcessor);
        }
    }

    /**
     * {@link #process(Object)} method.
     * Process input/output.
     *
     * @param inputOutput Input/output.
     * @return Processed input/output.
     * @throws JsonProcessingException JSON processing exception.
     */
    public Object process(Object inputOutput) throws JsonProcessingException {
        for (Object processor : processors.get().values()) {
            if (processor instanceof StringProcessingAdviserIface && inputOutput instanceof String) {
                inputOutput = ((StringProcessingAdviserIface) processor).processString((String) inputOutput);
            } else if (processor instanceof ByteProcessingAdviserIface && inputOutput instanceof byte[]) {
                inputOutput = ((ByteProcessingAdviserIface) processor).processBytes((byte[]) inputOutput);
            }
        }
        return inputOutput;
    }
}
