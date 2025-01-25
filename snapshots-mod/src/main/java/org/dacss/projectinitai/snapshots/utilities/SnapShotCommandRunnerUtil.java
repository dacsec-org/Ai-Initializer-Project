package org.dacss.projectinitai.snapshots.utilities;
/**/
/**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * <h1>{@link SnapShotCommandRunnerUtil}</h1>
 */
public class SnapShotCommandRunnerUtil {

    private static final Logger log = LoggerFactory.getLogger(SnapShotCommandRunnerUtil.class);
    /**
     * The BTRFS command to execute.
     */
    private static final String BTRFS_COMMAND = "sudo btrfs subvolume /home/$USER/.ai-initializer-project/models/.snapshots/";

    /**
     * Executes a btrfs subvolume command.
     * @param subcommand the subcommand to append to the btrfs command.
     * @param args the arguments to pass to the subcommand
     */
    public static void executeCommand(String subcommand, String... args) {
        String command = switch (subcommand) {
            case "snapshot" -> "%ssnapshot ".formatted(BTRFS_COMMAND);
            case "find-new" -> "%sfind-new ".formatted(BTRFS_COMMAND);
            case "delete" -> "%sdelete ".formatted(BTRFS_COMMAND);
            case "list" -> "%slist ".formatted(BTRFS_COMMAND);
            case "copy" -> "%scopy ".formatted(BTRFS_COMMAND);
            default -> throw new IllegalArgumentException(MessageFormat.format("Invalid subcommand: {0}", subcommand));
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
