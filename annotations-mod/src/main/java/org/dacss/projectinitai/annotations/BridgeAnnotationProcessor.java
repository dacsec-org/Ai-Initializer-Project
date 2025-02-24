package org.dacss.projectinitai.annotations;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.SourceVersion;
import java.util.Set;
import java.util.HashSet;

@SupportedAnnotationTypes("org.dacss.projectinitai.annotations.Bridge")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class BridgeAnnotationProcessor extends AbstractProcessor {

    private static final Set<String> registeredServices = new HashSet<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Bridge.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                Bridge bridge = typeElement.getAnnotation(Bridge.class);
                String serviceName = bridge.value();
                registeredServices.add(serviceName);
            }
        }
        return true;
    }

    public static Set<String> getRegisteredServices() {
        return registeredServices;
    }
}
