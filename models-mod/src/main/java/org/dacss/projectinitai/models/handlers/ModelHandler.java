package org.dacss.projectinitai.models.handlers;
/**/
import org.dacss.projectinitai.models.utilities.DestroyModelUtil;
import org.dacss.projectinitai.models.utilities.MergeModelUtil;
/**/
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <h1>{@link ModelHandler}</h1>
 * <p>
 *     Handler for merging and destroying models.
 * </p>
 */
@Component
public class ModelHandler {

    /**
     * Merges two models using the {@link MergeModelUtil}.
     * @param modelPath1 The path of the first model.
     * @param modelPath2 The path of the second model.
     */
    public void mergeModels(String modelPath1, String modelPath2) throws IOException {
        MergeModelUtil.mergeModels(modelPath1, modelPath2);
    }

    /**
     * Destroys a model using the {@link DestroyModelUtil}.
     * @param modelPath The path of the model to be destroyed.
     */
    public void destroyModel(String modelPath) {
        DestroyModelUtil.destroyModel(modelPath);
    }
}
