package org.dacss.projectinitai.annotations;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.SourceVersion;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link BridgeAnnotationProcessor}</h1>
 * Processes the {@link Bridge} annotation and generates Spring REST Controllers 
 * automatically for the annotated classes using the specified service name.
 * This class is an implementation of the {@link AbstractProcessor} and utilizes
 * the annotation processing environment to dynamically create source files.
 * <p>
 * The main functionality includes:
 * - Identifying classes annotated with {@link Bridge}.
 * - Generating Spring REST Controllers dynamically based on the annotated class 
 *   and its methods.
 * - Mapping methods and attributes of the annotated class into REST endpoints.
 * </p>
 * This processor supports annotation processing for Java Source Version 21.
 * <p>
 * See {@link #process(Set, RoundEnvironment)} for detailed operation.
 */
@SupportedAnnotationTypes("org.dacss.projectinitai.annotations.Bridge")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BridgeAnnotationProcessor extends AbstractProcessor {

    private static final Logger log = LoggerFactory.getLogger(BridgeAnnotationProcessor.class);

    /**
     * <h3>{@link #process(Set, RoundEnvironment)}</h3>
     * Processes the annotations provided to the annotation processor and generates
     * necessary artifacts such as Spring REST Controllers for classes annotated with
     * the {@link Bridge} annotation.
     *
     * @param annotations A set of annotation types to be processed by this processor.
     * @param roundEnv The environment for information about the current and prior
     *                 round of annotation processing.
     * @return A boolean indicating whether the annotations have been processed
     *         by this processor.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        log.info("Starting processing with BridgeAnnotationProcessor...");

        for (Element element : roundEnv.getElementsAnnotatedWith(Bridge.class)) {
            log.info("Processing element: {}", element);
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;

                // Retrieve the @Bridge annotation value
                Bridge bridge = typeElement.getAnnotation(Bridge.class);
                String serviceName = bridge.value(); // The service name specified in the annotation

                // Parameters for the generated class
                String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).toString() + ".generated";
                String className = typeElement.getSimpleName() + "Controller";

                try {
                    // Generate the Spring REST Controller class
                    generateControllerClass(packageName, className, typeElement, serviceName);
                } catch (IOException e) {
                    log.error("Failed to generate controller class: ", e);
                }
            }
        }
        return true;
    }

    /**
     * <h3>{@link #generateControllerClass(String, String, TypeElement, String)}</h3>
     * Generates a Spring REST Controller for the annotated class.
     *
     * @param packageName The package for the generated controller
     * @param className The name of the generated controller class
     * @param typeElement The class annotated with @Bridge
     * @param serviceName The service name (from the @Bridge annotation)
     */
    private void generateControllerClass(String packageName, String className, TypeElement typeElement, String serviceName) throws IOException {
        // Use Java Filer utility to create the Java source file
        JavaFileObject classFile = processingEnv.getFiler().createSourceFile(packageName + "." + className);

        try (Writer writer = classFile.openWriter()) {
            // Start generating the Spring REST Controller class
            writer.write(generateControllerHeader(packageName, className, typeElement, serviceName));

            // Reflect over service methods and generate REST endpoints dynamically
            for (Element enclosedElement : typeElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    ExecutableElement methodElement = (ExecutableElement) enclosedElement;
                    writer.write(generateRestEndpoint(methodElement, typeElement));
                }
            }

            // Close the class definition
            writer.write("}\n");
        }
        System.out.println("Generated Controller class: " + packageName + "." + className);
    }

    /**
     * <h3>{@link #generateControllerHeader(String, String, TypeElement, String)}</h3>
     * Generates the header for a Spring REST Controller class including package declaration, import statements, 
     * class annotations, and constructor for dependency injection.
     *
     * @param packageName The name of the package where the generated controller will reside.
     * @param className The name of the generated controller class.
     * @param typeElement The annotated class element used to retrieve the service class details.
     * @param serviceName The name of the service, typically derived from the @Bridge annotation.
     * @return A string containing the header code of the controller class in Java syntax.
     */
    private String generateControllerHeader(String packageName, String className, TypeElement typeElement, String serviceName) {
        return String.format(
                """
                        package %s;
                        
                        import org.springframework.web.bind.annotation.*;
                        import org.springframework.beans.factory.annotation.Autowired;
                        import %s;
                        
                        @RestController
                        @RequestMapping("/api/%s")
                        public class %s {
                        
                            private final %s service;
                        
                            @Autowired
                            public %s(%s service) {
                                this.service = service;
                            }
                        
                        """,
                packageName,                                         // Package name
                typeElement.getQualifiedName(),                      // Fully qualified name of the service class
                serviceName,                                         // Base path from @Bridge annotation
                className,                                           // Controller class name
                typeElement.getSimpleName(),                         // Service class simple name
                className,                                           // Controller class name again
                typeElement.getSimpleName()                          // Service class simple name again
        );
    }

    /**
     * <h3>{@link #generateRestEndpoint(ExecutableElement, TypeElement)}</h3>
     * Generates a Spring REST endpoint mapped to a specific method in a service class. 
     * This method dynamically constructs the REST endpoint based on the provided method details 
     * including its name, parameters, and return type. It also detects any usage of enum 
     * types within the method parameters.
     *
     * @param methodElement The method element from the service class that represents a single method 
     *                      to be exposed as a REST endpoint.
     * @param typeElement   The type element representing the class containing the annotated `@Bridge` service.
     * @return A string containing the generated code snippet for the Spring REST endpoint 
     *         that corresponds to the provided method.
     */
    private String generateRestEndpoint(ExecutableElement methodElement, TypeElement typeElement) {
        String methodName = methodElement.getSimpleName().toString();
        List<? extends VariableElement> parameters = methodElement.getParameters();
        TypeMirror returnType = methodElement.getReturnType();

        // Generate a unique path name for this method
        String path = methodName.toLowerCase();

        // Look for an enum parameter in the method
        Optional<String> enumType = parameters.stream()
                .filter(param -> param.asType().getKind() == TypeKind.DECLARED)
                .map(param -> (DeclaredType) param.asType())
                .filter(declaredType -> declaredType.asElement().getKind() == ElementKind.ENUM)
                .map(declaredType -> declaredType.asElement().toString())
                .findFirst();

        // Dynamic handling of method parameters (with potential enum)
        StringBuilder methodParams = new StringBuilder();
        StringBuilder requestParams = new StringBuilder();
        for (VariableElement parameter : parameters) {
            String paramName = parameter.getSimpleName().toString();
            String paramType = parameter.asType().toString();
            methodParams.append("@RequestParam ").append(paramType).append(" ").append(paramName).append(", ");
            requestParams.append(paramName).append(", ");
        }

        // Trim trailing commas
        if (!methodParams.isEmpty()) {
            methodParams.setLength(methodParams.length() - 2);
            requestParams.setLength(requestParams.length() - 2);
        }

        return String.format(
                """
                            @GetMapping("/%s")
                            public %s %s(%s) {
                                %s
                                return service.%s(%s);
                            }
                        
                        """,
                path,                       // Endpoint path
                returnType.toString(),      // Return type
                methodName,                 // Controller method name
                methodParams,               // Method RequestParams
                enumType.map(enumName -> String.format("// Detected enum: %s", enumName)).orElse(""), // Enum detection comment for clarity
                methodName,                 // Service method name
                requestParams               // RequestParam arguments for service method
        );
    }
}
