package org.dacss.projectinitai.loader;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.components.ContextualAdviserComp;
import org.dacss.projectinitai.components.ProcessorFactoryComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.manchester.tornado.api.DRMode;
import uk.ac.manchester.tornado.api.Policy;
import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;

import java.io.IOException;

@Slf4j
@Component
public class ParallelizedModelLoader {

    public ParallelizedModelLoader() {
    }

    private byte[] loadModelWithParallelLoop(String modelPath) throws IOException {
        try {
            byte[] model = ModelDirectoryHandler.loadModel(modelPath);
        } catch (IOException e) {
            log.error("Error loading model: {}", e.getMessage());
        }
        byte[] modelData = ModelDirectoryHandler.loadModel(modelPath);

        TaskGraph taskGraph = new TaskGraph("s0")
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, modelData)
                .task("loadModel", () -> {
                    for (@Parallel int i = 0; i < modelData.length; i++) {
                        //FIXME: ASAP this should be replaced with a real model loading process
                        modelData[i] = (byte) (modelData[i] + 1);
                    }
                })
                .transferToHost(DataTransferMode.EVERY_EXECUTION, modelData);

        TornadoExecutionPlan executionPlan = new TornadoExecutionPlan();
        executionPlan.getDevice(0).getAvailableProcessors();
        executionPlan.withDynamicReconfiguration(Policy.PERFORMANCE, DRMode.PARALLEL).execute();

        return modelData;
    }
}
