package org.dacss.projectinitai.snapshots;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SnapShotHandler {

    private final SnapShotCreator creator;
    private final SnapShotLister lister;
    private final SnapShotDestroyer destroyer;
    private final SnapShotCloner cloner;
    private final SnapShotCommandRunner commandRunner;

    public SnapShotHandler(SnapShotCreator creator, SnapShotLister lister, SnapShotDestroyer destroyer, SnapShotCloner cloner, SnapShotCommandRunner commandRunner) {
        this.creator = creator;
        this.lister = lister;
        this.destroyer = destroyer;
        this.cloner = cloner;
        this.commandRunner = commandRunner;
    }

    public void createSnapshot(String source, String destination) {
        try {
            commandRunner.executeCommand("snapshot", source, destination);
        } catch (IOException | InterruptedException e) {
            log.error("Failed to create snapshot", e);
        }
    }

    public void listSnapshots(String directory) {
        try {
            lister.listSnapshots(directory).forEach(log::info);
        } catch (IOException e) {
            log.error("Failed to list snapshots", e);
        }
    }

    public void deleteSnapshot(String snapshotPath) {
        try {
            commandRunner.executeCommand("delete", snapshotPath);
        } catch (IOException | InterruptedException e) {
            log.error("Failed to delete snapshot", e);
        }
    }

    public void copySnapshot(String source, String destination) {
        try {
            commandRunner.executeCommand("copy", source, destination);
        } catch (IOException | InterruptedException e) {
            log.error("Failed to copy snapshot", e);
        }
    }
}
