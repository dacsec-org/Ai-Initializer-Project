package org.dacss.projectinitai;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.dacss.projectinitai.components.ContextualAdviserComp;
import org.dacss.projectinitai.loaders.LLMProcessorComp;
import org.dacss.projectinitai.components.ProcessorFactoryComp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <h1>{@link Application}</h1>
 * Application is the main class that starts the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "@fontsource/roboto-mono", version = "4.5.0")
@Theme(value = "ai-initializer-project", variant = Lumo.DARK)
public class Application implements AppShellConfigurator {

    private final ContextualAdviserComp<String> contextualAdviserComp;
    private final LLMProcessorComp llmProcessorComp;
    private final ProcessorFactoryComp processorFactoryComp;

    @Autowired
    public Application(ContextualAdviserComp<String> contextualAdviserComp, LLMProcessorComp llmProcessorComp, ProcessorFactoryComp processorFactoryComp) {
        this.contextualAdviserComp = contextualAdviserComp;
        this.llmProcessorComp = llmProcessorComp;
        this.processorFactoryComp = processorFactoryComp;
    }

    /**
     * {@link #main(String[])}
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public ContextualAdviserComp<String> getContextualAdviserComp() {
        return contextualAdviserComp;
    }

    public LLMProcessorComp getLlmProcessorComp() {
        return llmProcessorComp;
    }

    public ProcessorFactoryComp getProcessorFactoryComp() {
        return processorFactoryComp;
    }
}
