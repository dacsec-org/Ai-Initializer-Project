package org.dacss.projectinitai.system;

import reactor.core.publisher.Mono;

/**
 * <h1>{@link CpuCapSettings}</h1>
 * This class is used to get CPU stats, and set a cap on the CPU usage for the framework.
 * Defaults to the number of cores on the machine.
 */
public class CpuCapSettings {
    private static int cpuCap = Runtime.getRuntime().availableProcessors();

    public CpuCapSettings() {
    }

    public static Mono<Object> getCpuCapSettings() {
        return Mono.just(cpuCap);
    }

    public static void setCpuCap(int cap) {
        cpuCap = cap;
    }
}
