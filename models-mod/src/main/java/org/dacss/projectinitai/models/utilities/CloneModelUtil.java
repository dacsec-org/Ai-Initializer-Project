package org.dacss.projectinitai.models.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * <h1>{@link CloneModelUtil}</h1>
 * Utility class for clone model operations.
 */
public class CloneModelUtil {

    /**
     * <h2>{@link #cloneModel(String)}</h2>
     * Clone a model.
     *
     * @param modelPath The path of the model to clone.
     * @throws IOException If an I/O error occurs.
     */
    public static void cloneModel(String modelPath) throws IOException {
        File modelFile = new File(modelPath);
        if (!modelFile.exists()) {
            throw new IllegalArgumentException("Model file does not exist: " + modelPath);
        }

        String parentDir = modelFile.getParent();
        String clonedFileName = "clone_" + modelFile.getName();
        Path clonedFilePath = Path.of(parentDir, clonedFileName);

        Files.copy(modelFile.toPath(), clonedFilePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
