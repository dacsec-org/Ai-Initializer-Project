package org.dacss.projectinitai.system;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link SystemSettingsOptions}</h1>
 * Enum for system settings options.
 * Options:
 * <ul>
 *     <li>{@link #BACKUP}={@link BackupRestore#backupSettings(Flux)}</li>
 *     <li>{@link #CPU_CAP}={@link CpuCap}</li>
 *     <li>{@link #GPU_CAP}={@link GpuCap}</li>
 *     <li>{@link #LOGGING}={@link LoggingSettings}</li>
 *     <li>{@link #MEMORY_CAP}={@link MemoryCap}</li>
 *     <li>{@link #NOTIFICATIONS}={@link Notifications}</li>
 *     <li>{@link #RESTORE}={@link BackupRestore#restoreSettings(Flux)}</li>
 *     <li>{@link #STORAGE_CAP}={@link StorageCap}</li>
 *     <li>{@link #THEME}={@link ThemeSettings}</li>
 * </ul>
 */
public enum SystemSettingsOptions {
    BACKUP,
    CPU_CAP,
    GPU_CAP,
    LOGGING,
    MEMORY_CAP,
    NOTIFICATIONS,
    RESTORE,
    STORAGE_CAP,
    THEME
}
