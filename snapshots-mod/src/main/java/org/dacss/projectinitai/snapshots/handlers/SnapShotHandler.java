// SnapShotHandler.java
package org.dacss.projectinitai.snapshots.handlers;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.snapshots.services.SnapShotService;
import org.dacss.projectinitai.snapshots.utilities.*;
import org.dacss.projectinitai.utilities.*;

/**
 * <h1>{@link SnapShotHandler}</h1>
 * Handler class called from {@link SnapShotService} that creates, lists,
 * deletes, and copies snapshots.
 */
@Slf4j
public class SnapShotHandler {

    public SnapShotHandler() {}

    /**
     * Creates a snapshot of a directory.
     * @param source the source directory
     * @param destination the destination directory
     */
    public void createSnapshot(String source, String destination) {
        SnapShotCreatorUtil.createSnapshot(source, destination);
    }

    /**
     * Lists snapshots in a directory.
     * @param directory the directory to list snapshots from
     */
    public void listSnapshots(String directory) {
        SnapShotListerUtil.listSnapshots(directory).forEach(log::info);
    }

    /**
     * Deletes a snapshot.
     * @param snapshotPath the snapshot path
     */
    public void deleteSnapshot(String snapshotPath) {
        SnapShotDestroyerUtil.deleteSnapshot(snapshotPath);
    }

    /**
     * Copies a snapshot.
     * @param source the source snapshot
     * @param destination the destination snapshot
     */
    public void copySnapshot(String source, String destination) {
        SnapShotClonerUtil.copySnapshot(source, destination);
    }

    /**
     * Executes a command.
     * @param subcommand the subcommand
     * @param args the arguments
     */
    public void executeCommand(String subcommand, String... args) {
        SnapShotCommandRunnerUtil.executeCommand(subcommand, args);
    }
}
