package org.dacss.projectinitai.components;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.advisers.processors.*;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>{@link ProcessAdviserComp}</h1>
 * Component to register all the Processors.
 */
@Slf4j
@Component
public class ProcessAdviserComp {

    private final ThreadLocal<Map<Class<?>, Object>> processors =
            ThreadLocal.withInitial(HashMap::new);

    /**
     * {@link #ProcessAdviserComp()}
     * constructor to register all the Processors.
     */
    public ProcessAdviserComp() {
        registerProcessors(
                new TextProcessor(),
                new CsvProcessor(),
                new JsonProcessor(),
                new XmlProcessor(),
                new HtmlProcessor(),
                new VectorizationProcessor(),
                new EncodingProcessor(),
                new MissingValuesProcessor(),
                new ImageProcessor(),
                new AudioProcessor(),
                new VideoProcessor(),
                new DocumentProcessor(),
                new PdfProcessor(),
                new TokenizationProcessor()
        );
    }

    /**
     * {@link #registerProcessors(Object[])}
     * @param preProcessors Processors to be registered.
     */
    void registerProcessors(Object... preProcessors) {
        for (Object preProcessor : preProcessors) {
            processors.get().put(preProcessor.getClass(), preProcessor);
        }
    }

    /**
     * {@link #process(Object)}
     *
     * @param inputOutput user-input, and ai-output to be processed.
     */
    public Object process(Object inputOutput) {
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
