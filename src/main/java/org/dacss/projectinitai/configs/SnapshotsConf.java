package org.dacss.projectinitai.configs;

import org.dacss.projectinitai.snapshots.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnapshotsConf {

    @Bean
    public SnapShotHandler snapShotHandler(SnapShotCreator creator, SnapShotLister lister, SnapShotDestroyer destroyer, SnapShotCloner cloner, SnapShotCommandRunner commandRunner) {
        return new SnapShotHandler(creator, lister, destroyer, cloner, commandRunner);
    }

    @Bean
    public SnapShotCloner snapShotCloner() {
        return new SnapShotCloner();
    }

    @Bean
    public SnapShotDestroyer snapShotDestroyer() {
        return new SnapShotDestroyer();
    }

    @Bean
    public SnapShotCreator snapShotCreator() {
        return new SnapShotCreator();
    }

    @Bean
    public SnapShotLister snapShotLister() {
        return new SnapShotLister();
    }

    @Bean
    public SnapShotCommandRunner snapShotCommandRunner() {
        return new SnapShotCommandRunner();
    }
}
