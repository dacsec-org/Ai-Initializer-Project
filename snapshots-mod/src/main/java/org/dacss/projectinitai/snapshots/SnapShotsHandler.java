package org.dacss.projectinitai.snapshots;

import org.springframework.stereotype.Component;
import org.dacss.projectinitai.snapshots.utilities.*;

/**
 * <h1>{@link SnapShotsHandler}</h1>
 * Handler class for snapshot operations.
 */
@Component
public class SnapShotsHandler implements SnapShotsIface {

    private final SnapShotsService snapShotsService;

    /**
     * <h2>{@link #SnapShotsHandler()}</h2>
     * 0-arg constructor to instantiate the {@link SnapShotsService}.
     */
    public SnapShotsHandler() {
        this.snapShotsService = new SnapShotsService();
    }

    public String handleCreate(String source, String destination) {
        SnapShotCreatorUtil.createSnapshot(source, destination);
        return "Snapshot created successfully";
    }

    public String handleList(String directory) {
        SnapShotListerUtil.listSnapshots(directory).forEach(System.out::println);
        return "Snapshots listed successfully";
    }

    public String handleDelete(String snapshotPath) {
        SnapShotDestroyerUtil.deleteSnapshot(snapshotPath);
        return "Snapshot deleted successfully";
    }

    public String handleCopy(String source, String destination) {
        SnapShotClonerUtil.copySnapshot(source, destination);
        return "Snapshot copied successfully";
    }

    public String handleExecuteCommand(String subcommand, String... args) {
        SnapShotCommandRunnerUtil.executeCommand(subcommand, args);
        return "Command executed successfully";
    }

    /**
     * <h2>{@link SnapShotsIface#manageSnapshots()}</h2>
     * Perform snapshot management operations.
     */
    @Override
    public void manageSnapshots() {
        //todo: implement
    }
}
