package org.dacss.projectinitai.snapshots.services;
/**/
import org.dacss.projectinitai.snapshots.handlers.SnapShotHandler;
/**/
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SnapShotService}</h1>
 * The SnapShotService class is a service class that handles snapshot actions.
 * It is also an endpoint for the frontend to call the backend.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class SnapShotService {

    private final SnapShotHandler handle;

    public SnapShotService(SnapShotHandler handle) {
        this.handle = handle;
    }

    /**
     * This method handles the snapshot action.
     * @param action The action to be performed.
     * @param source The source path.
     * @param destination The destination path.
     */
    private void handleSnapshotAction(String action, String source, String destination) {
        //todo: set up destinations and sources to match the install scripts
        switch (action) {
            case "create":
                handle.createSnapshot(source, destination);
                break;
            case "list":
                handle.listSnapshots(source);
                break;
            case "update":
                handle.copySnapshot(source, destination);
                break;
            case "delete":
                handle.deleteSnapshot(source);
                break;
            default:
                throw new IllegalArgumentException(STR."Unknown action: \{action}");
        }
    }
}
