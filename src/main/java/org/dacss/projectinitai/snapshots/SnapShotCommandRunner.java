package org.dacss.projectinitai.snapshots;

import org.dacss.projectinitai.services.SnapShotService;

import java.io.IOException;

/**
 * This class is responsible for executing the snapshot commands.
 */
public class SnapShotCommandRunner {

    private String btrfsCommand = "sudo btrfs subvolume ";
    private SnapShotHandler snapShotHandler;

    public SnapShotCommandRunner() {

    }

    void executeCommand(String subcommand, String... args) throws IOException, InterruptedException {
        String command = switch (subcommand) {
            case "snapshot" -> btrfsCommand + "snapshot ";
            case "delete" -> btrfsCommand + "delete ";
            case "list" -> btrfsCommand + "list ";
            case "copy" -> btrfsCommand + "copy ";
            default -> throw new IllegalArgumentException("Unknown subcommand: " + subcommand);
        };

        ProcessBuilder processBuilder = new ProcessBuilder(command + String.join(" ", args));
        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new IOException("Command execution failed. Exit code: " + exitCode);
        }
    }
}
