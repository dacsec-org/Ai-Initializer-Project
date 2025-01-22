package org.dacss.projectinitai.snapshots;

import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.snapshots.utilities.*;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SnapShotsService}</h1>
 * Backend hilla endpoint service for snapshot operations.
 */
@Service
@BrowserCallable
public class SnapShotsService implements SnapShotsIface {

    /**
     * <h2>{@link #SnapShotsService()}</h2>
     * 0-arg constructor.
     */
    public SnapShotsService() {}

    /**
     * <h2>{@link #manageSnapshots(String, String, String)}</h2>
     * Perform snapshot management operations.
     *
     * @param action      The action to perform on the snapshot.
     * @param source      The source directory for the snapshot.
     * @param destination The destination directory for the snapshot.
     */
    @Override
    public void manageSnapshots(String action, String source, String destination) {
        switch (action.toLowerCase()) {
            case "create":
                SnapShotCreatorUtil.createSnapshot(source, destination);
                break;
            case "list":
                SnapShotListerUtil.listSnapshots(source);
                break;
            case "delete":
                SnapShotDestroyerUtil.deleteSnapshot(source);
                break;
            case "copy":
                SnapShotClonerUtil.copySnapshot(source, destination);
                break;
            case "execute":
                SnapShotCommandRunnerUtil.executeCommand(source, destination);
                break;
            default:
                throw new IllegalArgumentException(STR."Invalid action: \{action}");
        }
    }
}
