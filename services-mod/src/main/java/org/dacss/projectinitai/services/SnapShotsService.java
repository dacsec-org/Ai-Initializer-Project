package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.snapshots.SnapShotsActions;
import org.dacss.projectinitai.snapshots.SnapShotsIface;
import org.dacss.projectinitai.snapshots.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link SnapShotsService}</h1>
 * Backend hilla endpoint service for snapshot operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class SnapShotsService implements SnapShotsIface {

    private static final Logger log = LoggerFactory.getLogger(SnapShotsService.class);

    private static final String source = "/home/$USER/.ai-initializer-project/models/.snapshots/";
    private static final String destination = "/home/$USER/.ai-initializer-project/models/.snapshots/";

    public SnapShotsService() {}

    @Override
    public Flux<Object> manageSnapshots(SnapShotsActions action) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case CREATE -> SnapShotCreatorUtil.createSnapshot(source, destination);
                case LIST -> SnapShotListerUtil.listSnapshots(source);
                case LIST_DIRECTORIES -> SnapShotListerUtil.listSnapshotDirectories(source);
                case DELETE -> SnapShotDestroyerUtil.deleteSnapshot(source);
                case COPY -> SnapShotClonerUtil.copySnapshot(source, destination);
                case EXECUTE_COMMAND -> SnapShotCommandRunnerUtil.executeCommand(source, destination);
            };
        } catch (Exception snapshotsServiceExc) {
            log.error("{}: {}", snapshotsServiceExc.getClass().getSimpleName(), snapshotsServiceExc.getMessage());
            return Flux.empty();
        } finally {
            log.info("{}: {}", action, "Snapshot operation completed.");
        }
        assert flux != null;
        return flux;
    }
}
