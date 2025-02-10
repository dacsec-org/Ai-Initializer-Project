package org.dacss.projectinitai.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>{@link CpuCapSettingsTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link CpuCapSettings#getCpuCapSettings()}</li>
 *     <li>{@link CpuCapSettings#setCpuCap(int)}</li>
 *     <li>{@link CpuCapSettings#enableVirtualThreading(boolean)}</li>
 *     <li>{@link CpuCapSettings#getLocalCpuCores()}</li>
 * </ul>
 */
class CpuCapSettingsTest {

    @BeforeEach
    void setUp() {
        System.out.println("Setting up test environment");
        CpuCapSettings.setCpuCap(CpuCapSettings.getLocalCpuCores());
        CpuCapSettings.enableVirtualThreading(false);
    }

    @Test
    void getCpuCapSettings() {
        System.out.println("Running getCpuCapSettings test");
        int expectedCpuCap = CpuCapSettings.getLocalCpuCores();
        Flux<Object> cpuCapSettings = CpuCapSettings.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed getCpuCapSettings test with result: " + expectedCpuCap);

        System.out.println("Running getCpuCapSettings test with virtual threading enabled");
        CpuCapSettings.enableVirtualThreading(true);
        cpuCapSettings = CpuCapSettings.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed getCpuCapSettings test with virtual threading enabled, result: " + expectedCpuCap);
    }

    @Test
    void setCpuCap() {
        System.out.println("Running setCpuCap test");
        int newCpuCap = 4;
        CpuCapSettings.setCpuCap(newCpuCap);
        Flux<Object> cpuCapSettings = CpuCapSettings.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(newCpuCap)
                .verifyComplete();
        System.out.println("Completed setCpuCap test with result: " + newCpuCap);
    }

    @Test
    void enableVirtualThreading() {
        System.out.println("Running enableVirtualThreading test");
        CpuCapSettings.enableVirtualThreading(true);
        int expectedCpuCap = CpuCapSettings.getLocalCpuCores();
        Flux<Object> cpuCapSettings = CpuCapSettings.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed enableVirtualThreading test with virtual threading enabled, result: " + expectedCpuCap);

        System.out.println("Running enableVirtualThreading test with virtual threading disabled");
        CpuCapSettings.enableVirtualThreading(false);
        expectedCpuCap = CpuCapSettings.getLocalCpuCores();
        cpuCapSettings = CpuCapSettings.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed enableVirtualThreading test with virtual threading disabled, result: " + expectedCpuCap);
    }

    @Test
    void getLocalCpuCores() {
        System.out.println("Running getLocalCpuCores test");
        int localCpuCores = CpuCapSettings.getLocalCpuCores();
        assertEquals(Runtime.getRuntime().availableProcessors(), localCpuCores);
        System.out.println("Completed getLocalCpuCores test with result: " + localCpuCores);
    }
}
