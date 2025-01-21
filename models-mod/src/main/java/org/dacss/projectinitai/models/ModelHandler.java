package org.dacss.projectinitai.models;

import org.dacss.projectinitai.models.utilities.DestroyModelUtil;
import org.dacss.projectinitai.models.utilities.MergeModelUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <h1>{@link ModelHandler}</h1>
 * Handler class for model operations.
 */
@Component
public class ModelHandler implements ModelIface {

    private final ModelsService modelsService;

    /**
     * <h2>{@link #ModelHandler()}</h2>
     * 0-arg constructor to instantiate the {@link ModelsService}.
     */
    public ModelHandler() {
        this.modelsService = new ModelsService();
    }

    public String handleMerge(String modelPath1, String modelPath2) throws IOException {
        // Implement merge handling logic here
        MergeModelUtil.mergeModels(modelPath1, modelPath2);
        return "Models merged successfully";
    }

    public String handleDestroy(String modelPath) {
        // Implement destroy handling logic here
        DestroyModelUtil.destroyModel(modelPath);
        return "Model destroyed successfully";
    }

    public String handleCreate(String modelPath) {
        // Implement create handling logic here
        // Placeholder for create logic
        return "Model created successfully";
    }

    /**
     * <h2>{@link ModelIface#processModel()}</h2>
     * Perform model operations.
     */
    @Override
    public void processModel() {
        //todo: implement
    }
}
