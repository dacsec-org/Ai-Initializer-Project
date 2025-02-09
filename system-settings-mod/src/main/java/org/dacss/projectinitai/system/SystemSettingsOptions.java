package org.dacss.projectinitai.system;

import java.nio.file.Path;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link SystemSettingsOptions}</h1>
 * Enum for system settings options.
 * Options:
 * <ul>
 *     <li>{@link #CPU_CAP}={@link CpuCapSettings}</li>
 *     <li>{@link #GPU_CAP}={@link GpuCapSettings}</li>
 *     <li>{@link #MEMORY_CAP}={@link MemoryCapSettings}</li>
 *     <li>{@link #STORAGE_CAP}={@link StorageCapSettings}</li>
 *     <li>{@link #LOGGING}={@link LoggingSettings}</li>
 *     <li>{@link #NOTIFICATIONS}={@link NotificationsSettings}</li>
 *     <li>{@link #THEME}={@link ThemeSettings}</li>
 *     <li>{@link #BACKUP}={@link BackupSettings}</li>
 *     <li>{@link #RESTORE}={@link RestoreSettings}</li>
 *     <li>{@link #EXPORT}={@link ExportImportSettings#exportSettings(Flux, Path)}</li>
 *     <li>{@link #IMPORT}={@link ExportImportSettings#importSettings(Path)}</li>
 * </ul>
 */
public enum SystemSettingsOptions {

    CPU_CAP,
    GPU_CAP,
    MEMORY_CAP,
    STORAGE_CAP,
    LOGGING,
    NOTIFICATIONS,
    THEME,
    BACKUP,
    RESTORE,
    IMPORT,
    EXPORT
}
