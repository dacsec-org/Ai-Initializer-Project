package org.dacss.projectinitai.loaders.kernels;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.ac.manchester.tornado.api.*;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;

import java.io.IOException;

import static org.dacss.projectinitai.loaders.ModelDirectoryHandler.loadModel;

@Slf4j
@Component
public class DynamicModelLoaderKernel {

    public DynamicModelLoaderKernel() {
    }

    private byte[] loadModelWithKernel(String modelPath) throws IOException {
        try {
            byte[] model = loadModel(modelPath);
        } catch (IOException e) {
            log.error("Error loading model: {}", e.getMessage());
        }
        byte[] modelData = loadModel(modelPath);

        TaskGraph taskGraph = new TaskGraph("s0")
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, modelData)
                .task("loadModel", () -> {
                    KernelContext context = new KernelContext();
                    int idx = context.globalIdx;
                    if (idx < modelData.length) {
                        //FIXME: ASAP this should be replaced with a real model loading process
                        modelData[idx] = (byte) (modelData[idx] + 1);
                    }
                })
                .transferToHost(DataTransferMode.EVERY_EXECUTION, modelData);

        try (TornadoExecutionPlan executionPlan = new TornadoExecutionPlan()) {
            executionPlan.getDevice(0).getAvailableProcessors();
            executionPlan.withDynamicReconfiguration(Policy.PERFORMANCE, DRMode.PARALLEL).execute();
        } catch (TornadoExecutionPlanException e) {
            log.error("Error executing Tornado plan: {}", e.getMessage());
        }

        return modelData;
    }

    public byte[] getModel() throws IOException {
        return loadModelWithKernel("modelPath");
    }
}
