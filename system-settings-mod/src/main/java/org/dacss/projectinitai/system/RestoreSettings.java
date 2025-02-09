package org.dacss.projectinitai.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link RestoreSettings}</h1>
 * This class provides methods to restore system settings.
 */
@Service
public class RestoreSettings {

    /**
     * {@link SystemSettingsRepository} instance.
     */
    private static SystemSettingsRepository repository;

    @Autowired
    public RestoreSettings(SystemSettingsRepository repository) {
        RestoreSettings.repository = repository;
    }

    public Flux<Object> restoreSettings(Flux<SystemSettingsEntity> settings) {
        return repository.deleteAll()
                .thenMany(repository.saveAll(settings))
                .thenMany(Flux.empty());
    }

    public static Flux<Object> getRestoreSettings() {
        return repository.findAll().cast(Object.class);
    }
}
