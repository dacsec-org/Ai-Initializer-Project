package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.models.ModelIface;
import org.dacss.projectinitai.models.utilities.CloneModelUtil;
import org.dacss.projectinitai.models.utilities.CreateNewModelUtil;
import org.dacss.projectinitai.models.utilities.DestroyModelUtil;
import org.dacss.projectinitai.models.utilities.MergeModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;

import static org.dacss.projectinitai.models.utilities.CloneModelUtil.cloneModel;
import static org.dacss.projectinitai.models.utilities.CreateNewModelUtil.createNewModel;
import static org.dacss.projectinitai.models.utilities.DestroyModelUtil.destroyModel;
import static org.dacss.projectinitai.models.utilities.MergeModelUtil.mergeModels;

/**
 * <h1>{@link ModelsService}</h1>
 * Backend hilla endpoint service for model operations.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class ModelsService implements ModelIface {

    private static final Logger log = LoggerFactory.getLogger(ModelsService.class);

    /**
     * <h2>{@link #ModelsService()}</h2>
     * 0-arg constructor.
     */
    public ModelsService() {}

    /**
     * <h2>{@link #processModel(String, String, String)}</h2>
     * Perform model operations.
     *
     * @param action     The action to perform on the model.
     * @param modelPath1 The path of the first model.
     * @param modelPath2 The path of the second model (optional for destroy and create).
     */
    @Override
    public void processModel(String action, String modelPath1, String modelPath2) throws IOException {
        try {
            switch (action) {
                case "create":
                    createNewModel();
                    break;
                case "destroy":
                    destroyModel(modelPath1);
                    break;
                case "merge":
                    mergeModels(modelPath1, modelPath2);
                    break;
                case "clone":
                    cloneModel(modelPath1);
                    break;
                default:
                    throw new IllegalArgumentException(MessageFormat.format("Invalid action: {0}", action));
            }
        } catch (IOException modelsServiceExc) {
            log.error("Error processing model", modelsServiceExc);
            throw modelsServiceExc;
        }
    }
}
