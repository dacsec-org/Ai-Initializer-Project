package org.dacss.projectinitai.services;

import java.nio.file.Paths;
import org.dacss.projectinitai.system.*;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link SystemSettingsService}</h1>
 * Backend hilla endpoint service for system settings operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class SystemSettingsService implements SystemSettingsIface {

    private static final Logger log = LoggerFactory.getLogger(SystemSettingsService.class);

    /**
     * <h3>{@link #SystemSettingsService()}</h3>
     * Default 0-arg constructor.
     */
    public SystemSettingsService() {}

    /**
     * <h3>{@link #processSettings(SystemSettingsOptions)}</h3>
     */
    @Override
    public Flux<Object> processSettings(SystemSettingsOptions options) {
        Flux<Object> flux;
        try {
            flux = switch (options) {
                case CPU_CAP -> CpuCapSettings.getCpuCapSettings();
                case GPU_CAP -> GpuCapSettings.getGpuCapSettings();
                case MEMORY_CAP -> MemoryCapSettings.getMemoryCapSettings();
                case STORAGE_CAP -> StorageCapSettings.getStorageCapSettings();
                case LOGGING -> LoggingSettings.getLoggingSettings();
                case NOTIFICATIONS -> NotificationsSettings.getNotificationsSettings();
                case THEME -> ThemeSettings.getThemeSettings();
                case BACKUP -> BackupSettings.getBackupSettings();
                case RESTORE -> RestoreSettings.getRestoreSettings();
                case EXPORT -> ExportImportSettings.exportSettings(Flux.empty(), Paths.get("/path/to/export/file"));
                case IMPORT -> ExportImportSettings.importSettings(Paths.get("/path/to/import/file"));
            };
        } catch (Exception systemSettingsServiceExc) {
            log.error("{}: Error from SystemSettingsService processing settings:", options, systemSettingsServiceExc);
            return Flux.empty();
        } finally {
            log.info("SystemSettingsService settings processed: {}", options);
        }
        return flux;
    }
}
