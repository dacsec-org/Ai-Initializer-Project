package org.dacss.projectinitai.system.options;

import org.dacss.projectinitai.system.SystemSettingsEntity;
import org.dacss.projectinitai.system.SystemSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link BackupRestore}</h1>
 * Service class provides methods to back up and restore system settings.
 */
@Service
public class BackupRestore {

    private static SystemSettingsRepository repository;

    /**
     * <h3>{@link #BackupRestore(SystemSettingsRepository)}</h3>
     *
     * @param repository the repository to be used for system settings operations.
     */
    @Autowired
    public BackupRestore(SystemSettingsRepository repository) {
        BackupRestore.repository = repository;
    }

    /**
     * <h3>{@link #getBackupSettings()}</h3>
     * Retrieves the backed up system settings.
     *
     * @return a {@link Flux} of backed up settings.
     */
    public static Flux<Object> getBackupSettings() {
        Flux<SystemSettingsEntity> result = repository.findAll();
        return result.cast(Object.class);
    }

    /**
     * <h3>{@link #getRestoreSettings()}</h3>
     * Retrieves the settings to be restored.
     *
     * @return a {@link Flux} of settings to be restored.
     */
    public static Flux<Object> getRestoreSettings() {
        return getBackupSettings();
    }

    /**
     * <h3>{@link #backupSettings(Flux)}</h3>
     * Backs up the provided system settings.
     *
     * @param settings a {@link Flux} of system settings to be backed up.
     * @return a {@link Flux} of backed up settings.
     */
    public Flux<Object> backupSettings(Flux<Object> settings) {
        return repository.saveAll(settings.cast(SystemSettingsEntity.class)).cast(Object.class);
    }

    /**
     * <h3>{@link #restoreSettings(Flux)}</h3>
     * Restores the provided system settings.
     *
     * @param settings a {@link Flux} of system settings to be restored.
     * @return a {@link Flux} indicating the completion of the restore operation.
     */
    public Flux<Object> restoreSettings(Flux<SystemSettingsEntity> settings) {
        Mono<Void> deleteResult = repository.deleteAll();
        return deleteResult.thenMany(repository.saveAll(settings)).thenMany(Flux.empty());
    }
}
