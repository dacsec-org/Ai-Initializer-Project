package org.dacss.projectinitai.system;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link SystemSettingsRepository}</h1>
 * A repository interface that extends {@link ReactiveCrudRepository} for the {@link SystemSettingsEntity}.
 */
public interface SystemSettingsRepository extends ReactiveCrudRepository<SystemSettingsEntity, Long> {

    Mono<SystemSettingsEntity> findByKey(String key);

    Flux<SystemSettingsEntity> findByValue(String value);

    Flux<SystemSettingsEntity> findByCategory(String category);

    Flux<SystemSettingsEntity> findByGroup(String group);

    Flux<SystemSettingsEntity> findByUserId(String userId);
}
