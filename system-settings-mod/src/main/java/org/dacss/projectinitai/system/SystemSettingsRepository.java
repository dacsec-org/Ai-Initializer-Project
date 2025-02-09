package org.dacss.projectinitai.system;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link SystemSettingsRepository}</h1>
 * A repository interface that extends {@link ReactiveCrudRepository} for the {@link SystemSettingsEntity}.
 */
public interface SystemSettingsRepository extends ReactiveCrudRepository<SystemSettingsEntity, Long> {

    /**
     * <h3>{@link #findByKey(String)}</h3>
     * Finds a system setting by its key.
     *
     * @param key the key of the system setting
     * @return a {@link Mono} containing the system setting
     */
    Mono<SystemSettingsEntity> findByKey(String key);

    /**
     * <h3>{@link #findByValue(String)}</h3>
     * Finds all system settings with a specific value.
     *
     * @param value the value of the system settings
     * @return a {@link Flux} containing the system settings
     */
    Flux<SystemSettingsEntity> findByValue(String value);

    Flux<SystemSettingsEntity> findByCategory(String category);

    Flux<SystemSettingsEntity> findByGroup(String group);

    Flux<SystemSettingsEntity> findByUserId(String userId);
}
