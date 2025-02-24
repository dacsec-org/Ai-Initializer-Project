package org.dacss.projectinitai.system;

import java.util.Objects;
import org.dacss.projectinitai.system.options.BackupRestore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * <h1>{@link BackupRestoreTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link BackupRestore#backupSettings(Flux)}</li>
 *     <li>{@link BackupRestore#getBackupSettings()}</li>
 *     <li>{@link BackupRestore#restoreSettings(Flux)}</li>
 *     <li>{@link BackupRestore#getRestoreSettings()}</li>
 * </ul>
 */
public class BackupRestoreTest {
    @Mock
    SystemSettingsRepository repository;
    @InjectMocks
    BackupRestore backupRestore;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBackupSettings() {
        System.out.println("Running testBackupSettings");
        Flux<SystemSettingsEntity> settings = Flux.just(new SystemSettingsEntity());
        when(repository.saveAll(any(Publisher.class))).thenReturn(settings);

        Flux<Object> result = backupRestore.backupSettings(settings.cast(Object.class));
        assertEquals(result.collectList().block(), settings.collectList().block());
        System.out.println("Completed testBackupSettings");
    }

    @Test
    public void testGetBackupSettings() {
        System.out.println("Running testGetBackupSettings");
        Flux<SystemSettingsEntity> settings = Flux.just(new SystemSettingsEntity());
        when(repository.findAll()).thenReturn(settings);

        Flux<Object> result = BackupRestore.getBackupSettings();
        assertEquals(result.collectList().block(), settings.collectList().block());

        // Test for empty return value
        when(repository.findAll()).thenReturn(Flux.empty());
        result = BackupRestore.getBackupSettings();
        assertTrue(Objects.requireNonNull(result.collectList().block()).isEmpty());
        System.out.println("Completed testGetBackupSettings");
    }

    @Test
    public void testRestoreSettings() {
        System.out.println("Running testRestoreSettings");
        Flux<SystemSettingsEntity> settings = Flux.just(new SystemSettingsEntity());
        when(repository.deleteAll()).thenReturn(Mono.empty());
        when(repository.saveAll(any(Publisher.class))).thenReturn(settings);

        Flux<Object> result = backupRestore.restoreSettings(settings);
        assertTrue(Objects.requireNonNull(result.collectList().block()).isEmpty());

        // Test for empty return value
        when(repository.deleteAll()).thenReturn(Mono.empty());
        when(repository.saveAll(any(Publisher.class))).thenReturn(Flux.empty());
        result = backupRestore.restoreSettings(settings);
        assertTrue(Objects.requireNonNull(result.collectList().block()).isEmpty());
        System.out.println("Completed testRestoreSettings");
    }

    @Test
    public void testGetRestoreSettings() {
        System.out.println("Running testGetRestoreSettings");
        Flux<SystemSettingsEntity> settings = Flux.just(new SystemSettingsEntity());
        when(repository.findAll()).thenReturn(settings);

        Flux<Object> result = BackupRestore.getRestoreSettings();
        assertEquals(result.collectList().block(), settings.collectList().block());

        // Test for empty return value
        when(repository.findAll()).thenReturn(Flux.empty());
        result = BackupRestore.getRestoreSettings();
        assertTrue(Objects.requireNonNull(result.collectList().block()).isEmpty());
        System.out.println("Completed testGetRestoreSettings");
    }
}
