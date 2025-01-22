package org.dacss.projectinitai.security.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * <h1>{@link SecurityApiTokenUtil}</h1>
 * <p>
 *     Utility class for handling API tokens.
 *     for now we read a gitignore .env file to get the token from the
 *     /environments/.env file. We will pass this off to PAM later.
 * </p>
 */
public class SecurityApiTokenUtil {

    private static final String ENV_FILE_PATH = "environments/.env";
    private static final String API_TOKEN_KEY = "API_TOKEN";

    /**
     * Reads the API token from the .env file.
     *
     * @return The API token.
     * @throws IOException If there is an error reading the .env file.
     */
    public static String getApiToken() throws IOException {
        Properties properties = new Properties();
        try (var inputStream = Files.newInputStream(Paths.get(ENV_FILE_PATH))) {
            properties.load(inputStream);
        }
        return properties.getProperty(API_TOKEN_KEY);
    }
}
