package org.dacss.projectinitai.services;

import org.dacss.projectinitai.system.*;

import java.nio.file.Paths;
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
                case BACKUP -> BackupSettings.getBackupSettings();
                case CPU_CAP -> CpuCapSettings.getCpuCapSettings();
                case EXPORT -> ExportImportSettings.exportSettings(Flux.empty(), Paths.get("/path/to/export/file"));
                case GPU_CAP -> GpuCapSettings.getGpuCapSettings();
                case IMPORT -> ExportImportSettings.importSettings(Paths.get("/path/to/import/file"));
                case LOGGING -> LoggingSettings.getLoggingSettings();
                case MEMORY_CAP -> MemoryCapSettings.getMemoryCapSettings();
                case NOTIFICATIONS -> NotificationsSettings.getNotificationsSettings();
                case RESTORE -> RestoreSettings.getRestoreSettings();
                case STORAGE_CAP -> StorageCapSettings.getResults();
                case THEME -> ThemeSettings.getThemeSettings();
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
