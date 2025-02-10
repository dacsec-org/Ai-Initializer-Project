//package org.dacss.projectinitai.services;
//
//import org.dacss.projectinitai.services.SystemSettingsService;
//import org.dacss.projectinitai.system.*;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
//public class SystemSettingsServiceTest {
//
//    private SystemSettingsService systemSettingsService;
//
//    @BeforeMethod
//    public void setUp() {
//        systemSettingsService = new SystemSettingsService();
//    }
//
//    @Test
//    public void testProcessSettingsBackup() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.BACKUP);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof BackupSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsCpuCap() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.CPU_CAP);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof CpuCapSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsExport() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.EXPORT);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof ExportImportSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsGpuCap() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.GPU_CAP);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof GpuCapSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsImport() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.IMPORT);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof ExportImportSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsLogging() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.LOGGING);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof LoggingSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsMemoryCap() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.MEMORY_CAP);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof MemoryCapSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsNotifications() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.NOTIFICATIONS);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof NotificationsSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsRestore() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.RESTORE);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof RestoreSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsStorageCap() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.STORAGE_CAP);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof StorageCapSettings)
//                .verifyComplete();
//    }
//
//    @Test
//    public void testProcessSettingsTheme() {
//        Flux<Object> result = systemSettingsService.processSettings(SystemSettingsOptions.THEME);
//        StepVerifier.create(result)
//                .expectNextMatches(obj -> obj instanceof ThemeSettings)
//                .verifyComplete();
//    }
//}
