package org.dacss.projectinitai.messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>{@link MessagesModApplication}</h1>
 * microservice for the chat/messaging module.
 */
@SpringBootApplication
public class MessagesModApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesModApplication.class, args);
    }
}
