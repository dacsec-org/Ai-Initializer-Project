package org.dacss.projectinitai.system;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link GpuCapSettings}</h1>
 * class to get gpu stats, then set a total % cap for the framework so we dont starve the system.
 */
public class GpuCapSettings {
    public static Flux<Object> getGpuCapSettings() {
        //todo: implement tornadoVm to get/set gpu stats
        return Flux.just(0);
    }
}
