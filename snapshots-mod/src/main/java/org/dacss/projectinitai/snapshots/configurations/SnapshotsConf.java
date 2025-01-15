package org.dacss.projectinitai.snapshots.configurations;

import org.dacss.projectinitai.snapshots.handlers.SnapShotHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
