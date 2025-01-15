package org.dacss.projectinitai.processors.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import org.dacss.projectinitai.processors.interfaces.ByteProcessingAdviserIface;
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class ProcessAdviserComp {

    private final ThreadLocal<Map<Class<?>, Object>> processors =
            ThreadLocal.withInitial(HashMap::new);

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

    void registerProcessors(Object... preProcessors) {
        for (Object preProcessor : preProcessors) {
            processors.get().put(preProcessor.getClass(), preProcessor);
        }
    }

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
