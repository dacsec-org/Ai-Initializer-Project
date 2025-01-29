package org.dacss.projectinitai.security.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * <h1>{@link SecurityApiTokenUtil}</h1>
 * <p>
 *     Utility class for handling API tokens.
 *     for now we read an env file to get the token from the '/environments/.env' file. We will pass this off to PAM later.
 * </p>
 */
public class SecurityApiTokenUtil {

    private static final String ENV_FILE_PATH = "../environments/.env";
    private static final String API_TOKEN_KEY = "API_TOKEN";

    /**
     * <h3>{@link #getApiToken()}</h3>
     *
     * @return the API token
     */
    public static String getApiToken() throws IOException {
        Properties properties = new Properties();
        try (var inputStream = Files.newInputStream(Paths.get(ENV_FILE_PATH))) {
            properties.load(inputStream);
        } catch (IOException ioExc) {
            throw new IOException("Error reading the .env file", ioExc);
        }
        return properties.getProperty(API_TOKEN_KEY);
    }
}
