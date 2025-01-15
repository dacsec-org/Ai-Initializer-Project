// SnapShotCommandRunnerUtil.java
package org.dacss.projectinitai.snapshots.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.snapshots.handlers.SnapShotHandler;

import java.io.IOException;

/**
 * <h1>{@link SnapShotCommandRunnerUtil}</h1>
 * Utility class to run btrfs subvolume commands.
 */
@Slf4j
@UtilityClass
public class SnapShotCommandRunnerUtil {

    private String btrfsCommand = "sudo btrfs subvolume ";
    private SnapShotHandler snapShotHandler;

    /**
     * Executes a btrfs subvolume command.
     * @param subcommand the subcommand to execute
     * @param args the arguments to pass to the subcommand
     */
    public void executeCommand(String subcommand, String... args) {
        String command = switch (subcommand) {
            case "snapshot" -> btrfsCommand + "snapshot ";
            case "delete" -> btrfsCommand + "delete ";
            case "list" -> btrfsCommand + "list ";
            case "copy" -> btrfsCommand + "copy ";
            default -> throw new IllegalArgumentException("Unknown subcommand: " + subcommand);
        };

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command + String.join(" ", args));
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                log.error("Command execution failed. Exit code: {}", exitCode);
            }
        } catch (IOException | InterruptedException e) {
            log.error("Failed to execute command: {}", command, e);
        }
    }
}
