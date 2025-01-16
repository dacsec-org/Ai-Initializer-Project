package org.dacss.projectinitai.loaders.kernels;
/**/
import org.dacss.projectinitai.utilities.DirectoryFileUtil;
/**/
import org.springframework.stereotype.Component;
import uk.ac.manchester.tornado.api.*;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;

import java.io.IOException;

import static org.dacss.projectinitai.utilities.handlers.ModelDirectoryHandler.loadModel;

/**
 * <h1>{@link DynamicModelLoaderKernel}</h1>
 * <p>
 *     Kernel class for loading a model dynamically.
 * </p>
 */
@Component
public class DynamicModelLoaderKernel {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DynamicModelLoaderKernel.class);

    /**
     * {@link #DynamicModelLoaderKernel()} 0-parameter constructor.
     */
    public DynamicModelLoaderKernel() {
    }

    /**
     * {@link #loadModelWithKernel(String)} method.
     * <p>
     *     Loads a model with a kernel.
     * @return byte[] - returns the model data.
     * </p>
     */
    private byte[] loadModelWithKernel(String modelPath) throws IOException {
        byte[] model = loadModel(modelPath);
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

    /**
     * {@link #getModel()} method.
     * <p>
     *     Gets the model.
     * @return byte[] - returns the model.
     * </p>
     */
    public byte[] getModel() throws IOException {
        return loadModelWithKernel("modelPath");
    }
}
