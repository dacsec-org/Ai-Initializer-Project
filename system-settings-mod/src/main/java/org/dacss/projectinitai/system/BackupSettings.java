package org.dacss.projectinitai.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link BackupSettings}</h1>
 * service class provides methods to back up and restore system settings.
 */
@Service
public class BackupSettings {

    /**
     * {@link SystemSettingsRepository} instance.
     */
    private static SystemSettingsRepository repository;

    @Autowired
    public BackupSettings(SystemSettingsRepository repository) {
        BackupSettings.repository = repository;
    }

    public Flux<Object> backupSettings(Flux<Object> settings) {
        return repository.saveAll(settings.cast(SystemSettingsEntity.class)).cast(Object.class);
    }

    public static Flux<Object> getBackupSettings() {
        return repository.findAll().cast(Object.class);
    }
}
