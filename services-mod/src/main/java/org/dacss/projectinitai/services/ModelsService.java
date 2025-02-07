package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.models.ModelActions;
import org.dacss.projectinitai.models.ModelIface;
import org.dacss.projectinitai.models.utilities.CloneModelUtil;
import org.dacss.projectinitai.models.utilities.CreateNewModelUtil;
import org.dacss.projectinitai.models.utilities.DestroyModelUtil;
import org.dacss.projectinitai.models.utilities.MergeModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * <h1>{@link ModelsService}</h1>
 * Backend hilla endpoint service for model operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class ModelsService implements ModelIface {

    private static final Logger log = LoggerFactory.getLogger(ModelsService.class);

    public ModelsService() {}

    @Override
    public Flux<Object> processModel(ModelActions action, String modelPath1, String modelPath2) throws IOException {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case CREATE -> CreateNewModelUtil.createNewModel();
                case CLONE -> CloneModelUtil.cloneModel(modelPath1);
                case DESTROY -> DestroyModelUtil.destroyModel(modelPath1);
                case MERGE -> MergeModelUtil.mergeModels(modelPath1, modelPath2);
                case SETTINGS -> null;
                case TRAIN -> null;
            };
        } catch (IOException modelsServiceExc) {
            log.error("Error processing model", modelsServiceExc);
            throw modelsServiceExc;
        } finally {
            log.info("{}: {}", action, MessageFormat.format("Model {0} {1}", modelPath1, modelPath2));
        }
        assert flux != null;
        return flux;
    }
}
