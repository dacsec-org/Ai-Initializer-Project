package org.dacss.projectinitai.configurations;

import org.dacss.projectinitai.handlers.SnapShotHandler;
import org.dacss.projectinitai.utilities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * <h1>{@link SnapshotsConf}</h1>
 * Configuration class for Snapshots module.
 */
@Configuration
public class SnapshotsConf {

    /**
     * Bean for {@link SnapShotHandler}
     */
    @Bean
    public SnapShotHandler snapShotHandler() {
        return new SnapShotHandler();
    }
}
