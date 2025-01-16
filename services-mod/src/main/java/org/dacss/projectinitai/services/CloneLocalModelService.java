package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>{@link CloneLocalModelService}</h1>
 * Service class for cloning local LLM models using BTRFS snapshots with a
 * frontend view 'src/main/frontend/views/clone-model.tsx'.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class CloneLocalModelService {
    /*todo: make sure this can be deleted, 'snapshots-mod' takes its
       place. or convert it into another function and view as the matching
       '.tsx' frontend exists. no commands are run in the new class*/

    /**
     * Clones a local LLM model using BTRFS snapshots.
     * @param sourcePath The source path of the model.
     * @param snapshotPath The destination path for the snapshot.
     * @return A message indicating the result of the operation.
     */
    public String cloneModel(String sourcePath, String snapshotPath) {
        Path source = Paths.get(sourcePath);
        Path snapshot = Paths.get(snapshotPath);

        try {
            // Ensure the source path exists
            if (!Files.exists(source)) {
                return "Source path does not exist.";
            }

            // Create the snapshot using BTRFS
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "btrfs", "subvolume", "snapshot", source.toString(), snapshot.toString());
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                return "Model cloned successfully.";
            } else {
                return STR."Failed to clone model. Exit code: \{exitCode}";
            }
        } catch (IOException | InterruptedException cloneIoExc) {
            return STR."Error cloning model: \{cloneIoExc.getMessage()}";
        }
    }
}
