package org.dacss.projectinitai.system;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link CpuCap}</h1>
 * This class is used to get CPU stats, and set a cap on the CPU usage for the framework.
 * For now defaults to the number of cores on the machine.
 */
public class CpuCap {
    private static int cpuCap;
    private static boolean virtualThreadingEnabled = false;

    static {
        updateCpuCap();
    }

    /**
     * <h3>{@link #CpuCap()}</h3>
     * Default 0-arg constructor.
     */
    private CpuCap() {}

    /**
     * <h3>{@link #getCpuCapSettings()}</h3>
     * Returns the CPU cap settings.
     *
     * @return
     */
    public static Flux<Object> getCpuCapSettings() {
        return Flux.just(cpuCap);
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
        updateCpuCap();
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

    /**
     * <h3>{@link #updateCpuCap()}</h3>
     * Updates the CPU cap based on the current settings.
     */
    private static void updateCpuCap() {
        cpuCap = getLocalCpuCores();
    }
}
