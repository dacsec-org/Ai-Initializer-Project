package org.dacss.projectinitai.system;

import org.dacss.projectinitai.system.options.CpuCap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>{@link CpuCapTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link CpuCap#getCpuCapSettings()}</li>
 *     <li>{@link CpuCap#setCpuCap(int)}</li>
 *     <li>{@link CpuCap#enableVirtualThreading(boolean)}</li>
 *     <li>{@link CpuCap#getLocalCpuCores()}</li>
 * </ul>
 */
class CpuCapTest {

    @BeforeEach
    void setUp() {
        System.out.println("Setting up test environment");
        CpuCap.setCpuCap(CpuCap.getLocalCpuCores());
        CpuCap.enableVirtualThreading(false);
    }

    @Test
    void getCpuCapSettings() {
        System.out.println("Running getCpuCapSettings test");
        int expectedCpuCap = CpuCap.getLocalCpuCores();
        Flux<Object> cpuCapSettings = CpuCap.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed getCpuCapSettings test with result: " + expectedCpuCap);

        System.out.println("Running getCpuCapSettings test with virtual threading enabled");
        CpuCap.enableVirtualThreading(true);
        cpuCapSettings = CpuCap.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed getCpuCapSettings test with virtual threading enabled, result: " + expectedCpuCap);
    }

    @Test
    void setCpuCap() {
        System.out.println("Running setCpuCap test");
        int newCpuCap = 4;
        CpuCap.setCpuCap(newCpuCap);
        Flux<Object> cpuCapSettings = CpuCap.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(newCpuCap)
                .verifyComplete();
        System.out.println("Completed setCpuCap test with result: " + newCpuCap);
    }

    @Test
    void enableVirtualThreading() {
        System.out.println("Running enableVirtualThreading test");
        CpuCap.enableVirtualThreading(true);
        int expectedCpuCap = CpuCap.getLocalCpuCores();
        Flux<Object> cpuCapSettings = CpuCap.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed enableVirtualThreading test with virtual threading enabled, result: " + expectedCpuCap);

        System.out.println("Running enableVirtualThreading test with virtual threading disabled");
        CpuCap.enableVirtualThreading(false);
        expectedCpuCap = CpuCap.getLocalCpuCores();
        cpuCapSettings = CpuCap.getCpuCapSettings();
        StepVerifier.create(cpuCapSettings)
                .expectNext(expectedCpuCap)
                .verifyComplete();
        System.out.println("Completed enableVirtualThreading test with virtual threading disabled, result: " + expectedCpuCap);
    }

    @Test
    void getLocalCpuCores() {
        System.out.println("Running getLocalCpuCores test");
        int localCpuCores = CpuCap.getLocalCpuCores();
        assertEquals(Runtime.getRuntime().availableProcessors(), localCpuCores);
        System.out.println("Completed getLocalCpuCores test with result: " + localCpuCores);
    }
}
