package org.dacss.projectinitai.starter.configurations;

import java.io.IOException;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>{@link BrowserLauncherConfig}</h1>
 * Opens the default browser on the user's system when the application is launched.
 */
/*uncomment to enable(after frontend compiling properly)*/
//@Configuration
public class BrowserLauncherConfig {

    /**
     * @return {@link ApplicationRunner} after 3 seconds of launching the application.
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            try {
                // Wait for 3 seconds before launching the browser
                Thread.sleep(3000);
                // Open the default browser
                Runtime.getRuntime().exec(new String[]{"sh", "-c", "xdg-open http://localhost:30320"});
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        };
    }
}
