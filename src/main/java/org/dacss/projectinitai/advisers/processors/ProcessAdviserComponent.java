package org.dacss.projectinitai.advisers.processors;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>{@link ProcessAdviserComponent}</h1>
 * Component to register all the Processors.
 */
@Component
public class ProcessAdviserComponent {

    private final ThreadLocal<Map<Class<?>, ProcessingAdviserIface<?>>> processors =
            ThreadLocal.withInitial(HashMap::new);

    /**
     * {@link #ProcessAdviserComponent()}
     * constructor to register all the Processors.
     */
    public ProcessAdviserComponent() {
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
     * {@link #registerProcessors(ProcessingAdviserIface[])}
     * @param preProcessors Processors to be registered.
     * @param <T> Type of Processor.
     */
    @SafeVarargs
    private <T> void registerProcessors(ProcessingAdviserIface<? extends T>... preProcessors) {
        for (ProcessingAdviserIface<? extends T> preProcessor : preProcessors) {
            processors.get().put(preProcessor.getClass(), preProcessor);
        }
    }


}
