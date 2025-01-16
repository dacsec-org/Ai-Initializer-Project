package org.dacss.projectinitai.frontend;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>{@link ProjectAiInitializerApplication}</h1>
 * ProjectAiInitializerApplication is the main class that starts the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "@fontsource/roboto-mono", version = "4.5.0")
@Theme(value = "ai-initializer-project", variant = Lumo.DARK)
public class ProjectAiInitializerApplication implements AppShellConfigurator {

    /**
     * {@link #main(String[])}
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjectAiInitializerApplication.class, args);
    }
}
