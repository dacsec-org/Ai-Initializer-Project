package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.system.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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
     * @param systemSettingsOptions {@link SystemSettingsOptions}
     * @return Mono<Object>
     */
    @Override
    public Mono<Object> processSettings(SystemSettingsOptions systemSettingsOptions) {
        Mono<Object> mono;
        try {
            mono = switch (systemSettingsOptions) {
                case CPU_CAP -> CpuCapSettings.getCpuCapSettings();
                case GPU_CAP -> GpuCapSettings.getGpuCapSettings();
                case MEMORY_CAP -> MemoryCapSettings.getMemoryCapSettings();
                case STORAGE_CAP -> StorageCapSettings.getStorageCapSettings();
                case LOGGING -> LoggingSettings.getLoggingSettings();
                case NOTIFICATIONS -> NotificationsSettings.getNotificationsSettings();
                case THEME -> ThemeSettings.getThemeSettings();
                case BACKUP -> BackupSettings.getBackupSettings();
                case RESTORE -> RestoreSettings.getRestoreSettings();
            };
        } catch (Exception systemSettingsServiceExc) {
            log.error("{}: Error from SystemSettingsService processing settings:", systemSettingsOptions, systemSettingsServiceExc);
            return Mono.empty();
        } finally {
            log.info("SystemSettingsService settings processed: {}", systemSettingsOptions);
        }
        return mono;
    }
}
