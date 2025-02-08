package org.dacss.projectinitai.system;

import reactor.core.publisher.Mono;

/**
 * <h1>{@link CpuCapSettings}</h1>
 * This class is used to get CPU stats, and set a cap on the CPU usage for the framework.
 * Defaults to the number of cores on the machine.
 */
public class CpuCapSettings {
    private static int cpuCap = Runtime.getRuntime().availableProcessors();
    private static boolean virtualThreadingEnabled = false;

    /**
     * <h3>{@link #CpuCapSettings()}</h3>
     * Default 0-arg constructor.
     */
    public CpuCapSettings() {}

    /**
     * <h3>{@link #getCpuCapSettings()}</h3>
     * Returns the CPU cap settings.
     *
     * @return
     */
    public static Mono<Object> getCpuCapSettings() {
        return Mono.just(virtualThreadingEnabled ? cpuCap * 2 : cpuCap);
    }

    /**
     * <h3>{@link #setCpuCap(int)} </h3>
     * Sets the CPU cap.
     *
     * @param cap
     */
    public static void setCpuCap(int cap) {
        cpuCap = cap;
    }

    /**
     * <h3>{@link #enableVirtualThreading(boolean)}</h3>
     * Enables or disables virtual threading.
     *
     * @param enable True to enable, false to disable.
     */
    public static void enableVirtualThreading(boolean enable) {
        virtualThreadingEnabled = enable;
    }

    /**
     * <h3>{@link #getLocalCpuCores()}</h3>
     * Returns the number of CPU cores on the local machine.
     *
     * @return The number of CPU cores.
     */
    public static int getLocalCpuCores() {
        return Runtime.getRuntime().availableProcessors();
    }
}
/**/
