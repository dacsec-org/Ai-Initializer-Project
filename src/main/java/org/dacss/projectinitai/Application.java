package org.dacss.projectinitai;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.dacss.projectinitai.clients.UniversalChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * <h1>{@link Application}</h1>
 * Application is the main class that starts the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "@fontsource/roboto-mono", version = "4.5.0")
@Theme(value = "ai-initializer-project", variant = Lumo.DARK)
public class Application implements AppShellConfigurator, CommandLineRunner {

    private final UniversalChatClient chatClient;

    /**
     * {@link #Application(UniversalChatClient)}
     * @param chatClient The chat client to use.
     * @see UniversalChatClient
     */
    @Autowired
    public Application(UniversalChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * {@link #main(String[])}
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * {@link #Run(String...)}
     */
    @Override
    public void run(String... args) {
        if (args.length > 0 && "cli".equalsIgnoreCase(args[0])) {
            runCommandLineInterface();
        }
    }

    /**
     * {@link #runCommandLineInterface()}
     */
    private void runCommandLineInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message (type 'exit' to quit):");

        while (true) {
            String userMessage = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userMessage)) {
                break;
            }
            String response = chatClient.sendMessage(userMessage);
            System.out.println("LLM: " + response);
        }
        scanner.close();
    }
}
