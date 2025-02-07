package org.dacss.projectinitai.loaders;

import uk.ac.manchester.tornado.api.*;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>{@link LoadKernel}</h1>
 * <p>
 * Kernel class for loading a model dynamically on to the GPU.
 * We dynamically load the model so tornado chooses the best processor, rather than forcing it.
 * </p>
 */
public class LoadKernel {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoadKernel.class);

    /**
     * {@link #LoadKernel()}
     * 0-parameter constructor.
     */
    public LoadKernel() {
    }

    /**
     * {@link #loadModelKernel(String)}
     * Loads a model with kernel api.
     *
     * @return byte[] - returns the model data.
     */
    public byte[] loadModelKernel(String modelPath) {
        byte[] modelData = null;
        try {
            modelData = loadModel(modelPath);
            final byte[] finalModelData = modelData; // Make modelData effectively final

            TaskGraph taskGraph = new TaskGraph("s0")
                    .transferToDevice(DataTransferMode.FIRST_EXECUTION, finalModelData)
                    .task("loadModel", () -> {
                        KernelContext context = new KernelContext();
                        int idx = context.globalIdx;
                        if (idx < finalModelData.length) {
                            finalModelData[idx] = (byte) (finalModelData[idx] + 1);
                        }
                    })
                    .transferToHost(DataTransferMode.EVERY_EXECUTION, finalModelData);

            // Create an immutable task-graph
            ImmutableTaskGraph immutableTaskGraph = taskGraph.snapshot();

            try (TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph)) {
                executionPlan.getDevice(0).getAvailableProcessors();
                executionPlan.withDynamicReconfiguration(Policy.PERFORMANCE, DRMode.PARALLEL).execute();
            } catch (TornadoExecutionPlanException loadModelKernelExc) {
                log.error("Error executing Tornado plan: {}", loadModelKernelExc.getMessage());
            }
        } catch (IOException loadModelKernelIOExc) {
            log.error("Error loading model: {}", loadModelKernelIOExc.getMessage());
        }

        return modelData;
    }

    /**
     * {@link #loadModel(String)}
     * <p>
     * Loads a model from the file system.
     * </p>
     *
     * @param modelPath the path to the model.
     * @return byte[] - returns the model data.
     */
    private byte[] loadModel(String modelPath) throws IOException {
        Path path = Paths.get(modelPath);
        return Files.readAllBytes(path);
    }

    /**
     * {@link #getModel()}
     *
     * @return byte[] - returns the model.
     */
    public byte[] getModel() {
        return loadModelKernel("modelPath");
    }

    /**
     * {@link #kernelIsLoaded()}
     * Checks if the kernel is loaded.
     *
     * @return boolean - returns true if the kernel is loaded.
     */
    boolean kernelIsLoaded() {
        //todo: implement this method to check if the kernel is loaded
        return true;
    }
}
