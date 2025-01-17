package org.dacss.projectinitai.security.services;

import com.vaadin.flow.component.notification.Notification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class CredentialService {
    private static final Properties properties = new Properties();
    private static final String ENV_FILE = "environments/.env";
    private static final Logger logger = Logger.getLogger(CredentialService.class.getName());

    static {
        try (FileInputStream fis = new FileInputStream(ENV_FILE)) {
            properties.load(fis);
            logger.info("Credentials loaded from .env file");
        } catch (IOException e) {
            logger.severe(STR."Failed to load credentials from .env file: \{e.getMessage()}");
            Notification.show("Failed to load credentials from .env file", 3000, Notification.Position.MIDDLE);
        }
    }

    public static void saveCredentials(String username, String password) {
        try (FileOutputStream fos = new FileOutputStream(ENV_FILE)) {
            properties.setProperty("HUGGINGFACE_USERNAME", username);
            properties.setProperty("HUGGINGFACE_PASSWORD", password);
            properties.store(fos, null);
            logger.info("Credentials saved to .env file");
        } catch (IOException e) {
            logger.severe(STR."Failed to save credentials: \{e.getMessage()}");
            Notification.show("Failed to save credentials", 3000, Notification.Position.MIDDLE);
        }
    }

    public static String getApiToken() {
        return properties.getProperty("HUGGINGFACE_API_TOKEN");
    }
}
