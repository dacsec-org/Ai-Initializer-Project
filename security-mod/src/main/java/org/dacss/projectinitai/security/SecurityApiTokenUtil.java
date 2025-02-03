package org.dacss.projectinitai.security;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * <h1>{@link SecurityApiTokenUtil}</h1>
 * <p>
 *     Utility class for handling API tokens.
 *     For now, we read an env file to get the token from the '.env' file. We will pass this off to PAM later.
 * </p>
 */
@Component
public class SecurityApiTokenUtil {

    private static final String API_TOKEN_KEY = "API_TOKEN";

    /**
     * <h3>{@link #getApiToken()}</h3>
     * Retrieves the API token from the '.env' file.
     * The method performs the following steps:
     * <ul>
     *     <li>Determines the path to the '.env' file based on the environment (production or development).</li>
     *     <li>Reads the '.env' file to extract the API token.</li>
     *     <li>Returns the API token as a Flux.</li>
     * </ul>
     * <p>
     * The method uses two paths:
     * <ul>
     *     <li><b><code>productionPath</code></b>: Points to the user's home directory for production use.</li>
     *     <li><b><code>devPath</code></b>: Points to the root of the project for development use.</li>
     * </ul>
     *
     * @return a Flux containing the API token as an Object.
     * @throws IOException if there is an error reading the '.env' file or the API token is not found.
     */
    public static Flux<Object> getApiToken() {
        return Mono.fromCallable(() -> {
            String userHome = System.getProperty("user.home");
            String productionPath = userHome + "/.gnupg/pai-token/.env";
            String devPath = "home/pai/.gnupg/pai-token/.env";
            String envFilePath = Files.exists(Paths.get(productionPath)) ? productionPath : devPath;

            Properties properties = new Properties();
            try (var inputStream = Files.newInputStream(Paths.get(envFilePath))) {
                properties.load(inputStream);
                Object apiToken = properties.getProperty(API_TOKEN_KEY);
                if (apiToken != null) {
                    return apiToken;
                } else {
                    throw new IOException("API token not found in the .env file");
                }
            }
        })
        .subscribeOn(Schedulers.boundedElastic())
        .flux()
        .onErrorResume(e -> Flux.error(new IOException("Error reading the .env file", e)));
    }
}
