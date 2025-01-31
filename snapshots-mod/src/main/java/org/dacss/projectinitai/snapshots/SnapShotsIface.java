package org.dacss.projectinitai.snapshots;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link SnapShotsIface}</h1>
 */
@FunctionalInterface
public interface SnapShotsIface {

    Flux<Object> manageSnapshots(SnapShotsActions action);
}
