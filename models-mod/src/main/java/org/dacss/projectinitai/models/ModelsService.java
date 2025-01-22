package org.dacss.projectinitai.models;

import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.models.utilities.CreateNewModelUtil;
import org.dacss.projectinitai.models.utilities.DestroyModelUtil;
import org.dacss.projectinitai.models.utilities.MergeModelUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <h1>{@link ModelsService}</h1>
 * Backend hilla endpoint service for model operations.
 */
@Service
@BrowserCallable
public class ModelsService implements ModelIface {

    /**
     * <h2>{@link #ModelsService()}</h2>
     * 0-arg constructor.
     */
    public ModelsService() {}

    /**
     * <h2>{@link #processModel(String, String, String)}</h2>
     * Perform model operations.
     *
     * @param action    The action to perform on the model.
     * @param modelPath1 The path of the first model.
     * @param modelPath2 The path of the second model (optional for destroy and create).
     */
    @Override
    public void processModel(String action, String modelPath1, String modelPath2) throws IOException {
        switch (action.toLowerCase()) {
            case "create":
                new CreateNewModelUtil().createNewModel();
                break;
            case "destroy":
                DestroyModelUtil.destroyModel(modelPath1);
                break;
            case "merge":
                MergeModelUtil.mergeModels(modelPath1, modelPath2);
                break;
            default:
                throw new IllegalArgumentException(STR."Unknown action: \{action}");
        }
    }
}
