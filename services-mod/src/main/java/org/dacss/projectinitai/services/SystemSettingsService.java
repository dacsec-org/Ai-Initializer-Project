package org.dacss.projectinitai.services;

import org.dacss.projectinitai.annotations.Bridge;
import org.dacss.projectinitai.system.*;

import org.dacss.projectinitai.system.options.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link SystemSettingsService}</h1>
 * Handles the processing of system settings.
 */
@Service
@Bridge("SystemSettingsService")
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
                case BACKUP -> BackupRestore.getBackupSettings();
                case CPU_CAP -> CpuCap.getCpuCapSettings();
                case GPU_CAP -> GpuCap.getGpuCapSettings();
                case LOGGING -> LoggingSettings.getLoggingSettings();
                case MEMORY_CAP -> MemoryCap.getMemoryStats();
                case NOTIFICATIONS -> Notifications.getNotificationsSettings();
                case RESTORE -> BackupRestore.getRestoreSettings();
                case STORAGE_CAP -> StorageCap.getResults();
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
