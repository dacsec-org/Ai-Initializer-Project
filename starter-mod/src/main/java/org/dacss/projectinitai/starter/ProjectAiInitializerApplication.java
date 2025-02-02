package org.dacss.projectinitai.starter;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * <h1>{@link ProjectAiInitializerApplication}</h1>
 * ProjectAiInitializerApplication is the main class that starts the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "@fontsource/roboto-mono", version = "4.5.0")
@Theme(value = "ai-initializer-project", variant = Lumo.DARK)
public class ProjectAiInitializerApplication implements AppShellConfigurator {

    /**
     * <h2>{@link #main(String[])}
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjectAiInitializerApplication.class, args);
    }

    /**
     * <h2>{@link #userRequestSink()}</h2>
     *
     * @return Sinks.Many<Object>
     */
    @Bean
    public Sinks.Many<Object> userRequestSink() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    /**
     * <h2>{@link #userRequestFlux(Sinks.Many)}</h2>
     *
     * @param userRequestSink
     * @return Flux<Object>
     */
    @Bean
    public Flux<Object> userRequestFlux(Sinks.Many<Object> userRequestSink) {
        return userRequestSink.asFlux();
    }

    /**
     * <h2>{@link #aiResponseSink()}</h2>
     *
     * @return Sinks.Many<Object>
     */
    @Bean
    public Sinks.Many<Object> aiResponseSink() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    /**
     * <h2>{@link #aiResponseFlux(Sinks.Many)}</h2>
     *
     * @param aiResponseSink
     * @return Flux<Object>
     */
    @Bean
    public Flux<Object> aiResponseFlux(Sinks.Many<Object> aiResponseSink) {
        return aiResponseSink.asFlux();
    }
}
