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

@SupportedAnnotationTypes("org.dacss.projectinitai.annotations.Bridge")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BridgeAnnotationProcessor extends AbstractProcessor {

    private static final Logger log = LoggerFactory.getLogger(BridgeAnnotationProcessor.class);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        log.info("Starting processing with BridgeAnnotationProcessor...");

        for (Element element : roundEnv.getElementsAnnotatedWith(Bridge.class)) {
            log.info("Processing element: {}", element);
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;

                Bridge bridge = typeElement.getAnnotation(Bridge.class);
                String serviceName = bridge.value();

                String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).toString() + ".generated";
                String className = typeElement.getSimpleName() + "Controller";

                try {
                    generateControllerClass(packageName, className, typeElement, serviceName);
                } catch (IOException e) {
                    log.error("Failed to generate controller class: ", e);
                }
            }
        }
        return true;
    }

    private void generateControllerClass(String packageName, String className, TypeElement typeElement, String serviceName) throws IOException {
        JavaFileObject classFile = processingEnv.getFiler().createSourceFile(packageName + "." + className);

        try (Writer writer = classFile.openWriter()) {
            writer.write(generateControllerHeader(packageName, className, typeElement, serviceName));

            for (Element enclosedElement : typeElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    ExecutableElement methodElement = (ExecutableElement) enclosedElement;
                    writer.write(generateRestEndpoint(methodElement, typeElement));
                }
            }

            writer.write("}\n");
        }
        log.info("Generated Controller class: {}.{}", packageName, className);
    }

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
                packageName,
                typeElement.getQualifiedName(),
                serviceName,
                className,
                typeElement.getSimpleName(),
                className,
                typeElement.getSimpleName()
        );
    }

    private String generateRestEndpoint(ExecutableElement methodElement, TypeElement typeElement) {
        String methodName = methodElement.getSimpleName().toString();
        List<? extends VariableElement> parameters = methodElement.getParameters();
        TypeMirror returnType = methodElement.getReturnType();

        String path = methodName.toLowerCase();

        Optional<String> enumType = parameters.stream()
                .filter(param -> param.asType().getKind() == TypeKind.DECLARED)
                .map(param -> (DeclaredType) param.asType())
                .filter(declaredType -> declaredType.asElement().getKind() == ElementKind.ENUM)
                .map(declaredType -> declaredType.asElement().toString())
                .findFirst();

        StringBuilder methodParams = new StringBuilder();
        StringBuilder requestParams = new StringBuilder();
        for (VariableElement parameter : parameters) {
            String paramName = parameter.getSimpleName().toString();
            String paramType = parameter.asType().toString();
            methodParams.append("@RequestParam ").append(paramType).append(" ").append(paramName).append(", ");
            requestParams.append(paramName).append(", ");
        }

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
                path,
                returnType.toString(),
                methodName,
                methodParams,
                enumType.map(enumName -> String.format("// Detected enum: %s", enumName)).orElse(""),
                methodName,
                requestParams
        );
    }
}
