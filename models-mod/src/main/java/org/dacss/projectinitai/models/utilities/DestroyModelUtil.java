package org.dacss.projectinitai.models.utilities;
/**/

import java.io.File;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link DestroyModelUtil}</h1>
 * <p>
 * Utility class for deleting local models.
 * </p>
 */
@Component
public class DestroyModelUtil {



    /**
     * {@link DestroyModelUtil#destroyModel(String)}
     * <p>
     * Deletes a model.
     * </p>
     *
     * @param modelPath The path of the model to be deleted.
     * @return True if the model was successfully deleted, false otherwise.
     */
    public static boolean destroyModel(String modelPath) {
        File modelFile = new File(modelPath);
        if (modelFile.exists()) {
            return modelFile.delete();
        } else {
            System.err.println("Model does not exist: " + modelPath);
            return false;
        }
    }
}
