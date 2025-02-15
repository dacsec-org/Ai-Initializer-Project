package org.dacss.projectinitai.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <h1>{@link BridgeAnnotationProcessor}</h1>
 * Annotation processor for the {@link Bridge} annotation.
 * <p>
 * This processor generates API handler classes for services annotated with {@link Bridge}.
 * The generated handlers expose the service methods as REST endpoints.
 * </p>
 */
@SupportedAnnotationTypes("org.dacss.projectinitai.annotations.Bridge")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BridgeAnnotationProcessor extends AbstractProcessor {

    private static final Log log = LogFactory.getLog(BridgeAnnotationProcessor.class);
    private static final String GENERATED_DIR = "generated";

    /**
     * <h3>{@link #process(Set, RoundEnvironment)}</h3>
     * {@link Bridge} annotations and generates the corresponding API handler classes.
     *
     * @param annotations The set of annotations to be processed.
     * @param roundEnv    The environment for information about the current and prior round.
     * @return true if the annotations are processed successfully.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Filer filer = processingEnv.getFiler();

        for (Element element : roundEnv.getElementsAnnotatedWith(Bridge.class)) {
            if (element instanceof TypeElement typeElement) {
                String className = typeElement.getSimpleName() + "ApiHandler";
                String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
                String serviceName = typeElement.getAnnotation(Bridge.class).value();
                String filePath = GENERATED_DIR + "/" + packageName.replace('.', '/') + "/" + className + ".java";

                try {
                    StringBuilder content = new StringBuilder();
                    if (Files.exists(Paths.get(filePath))) {
                        content.append(new String(Files.readAllBytes(Paths.get(filePath))));
                        int insertIndex = content.lastIndexOf("}");
                        content.insert(insertIndex, generateMethod(typeElement, serviceName));
                    } else {
                        content.append(generateClassHeader(packageName, className, serviceName));
                        content.append(generateMethod(typeElement, serviceName));
                        content.append("}\n");
                    }

                    JavaFileObject file = filer.createSourceFile(packageName + "." + className);
                    try (Writer writer = file.openWriter()) {
                        writer.write(content.toString());
                    }
                } catch (IOException bridgeProcessExc) {
                    log.error("Error processing bridge annotation:", bridgeProcessExc);
                }
            }
        }
        return true;
    }

    /**
     * <h3>{@link #generateClassHeader(String, String, String)}</h3>
     *
     * @param packageName The package name.
     * @param className   The class name.
     * @param serviceName The service name.
     * @return The generated class header as a string.
     */
    String generateClassHeader(String packageName, String className, String serviceName) {
        return "package " + packageName + ";\n\n" +
                "import org.dacss.projectinitai.annotations.BridgeRegistry;\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "import reactor.core.publisher.Flux;\n\n" +
                "@RestController\n" +
                "@RequestMapping(\"/" + serviceName + "\")\n" +
                "public class " + className + " {\n\n" +
                "    private final BridgeRegistry bridgeRegistry;\n\n" +
                "    public " + className + "(BridgeRegistry bridgeRegistry) {\n" +
                "        this.bridgeRegistry = bridgeRegistry;\n" +
                "    }\n\n";
    }

    /**
     * <h3>{@link #generateMethod(TypeElement, String)}</h3>
     *
     * @param typeElement The type element.
     * @param serviceName The service name.
     * @return The generated method as a string.
     */
    String generateMethod(TypeElement typeElement, String serviceName) {
        return "    @PostMapping(\"/processMessages\")\n" +
                "    public Flux<Object> processMessages(@RequestBody MessageAction action) {\n" +
                "        " + typeElement.getSimpleName() + " service = (" + typeElement.getSimpleName() + ") bridgeRegistry.getService(\"" + serviceName + "\");\n" +
                "        return service.processMessages(action);\n" +
                "    }\n\n";
    }
}
