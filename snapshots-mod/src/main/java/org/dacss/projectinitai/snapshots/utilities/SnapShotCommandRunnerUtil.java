package org.dacss.projectinitai.snapshots.utilities;
/**/
import org.dacss.projectinitai.snapshots.handlers.SnapShotHandler;
/**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <h1>{@link SnapShotCommandRunnerUtil}</h1>
 * <p>
 *     Utility class for executing BTRFS subvolume commands run byt the {@link SnapShotHandler}.
 * </p>
 */
public class SnapShotCommandRunnerUtil {

    private static final Logger log = LoggerFactory.getLogger(SnapShotCommandRunnerUtil.class);
    private static final String BTRFS_COMMAND = "sudo btrfs subvolume /home/$USER/.ai-initializer-project/models ";
    private SnapShotHandler snapShotHandler;

    /**
     * Executes a btrfs subvolume command.
     * @param subcommand the subcommand to execute
     * @param args the arguments to pass to the subcommand
     */
    public static void executeCommand(String subcommand, String... args) {
        String command = switch (subcommand) {
            /*todo: adjust commands(sudo btrfs subvolume /home/$USER/.ai-initializer-project/models list, snapshot, delete, copy...)*/
            case "snapshot" -> STR."\{BTRFS_COMMAND}create ";
            case "find-new" -> STR."\{BTRFS_COMMAND}find-new ";
            case "delete" -> STR."\{BTRFS_COMMAND}delete ";
            case "list" -> STR."\{BTRFS_COMMAND}list ";
            case "copy" -> STR."\{BTRFS_COMMAND}copy ";
            default -> throw new IllegalArgumentException(STR."Unknown subcommand: \{subcommand}");
        };

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command + String.join(" ", args));
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                log.error("Command execution failed. Exit code: {}", exitCode);
            }
        } catch (IOException | InterruptedException runCommandExc) {
            log.error("Failed to execute command: {}", command, runCommandExc);
        }
    }
}
