package org.dacss.projectinitai.loaders;
/**/
import org.springframework.stereotype.Component;
import uk.ac.manchester.tornado.api.*;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;

/**
 * <h1>{@link UnLoadKernel}</h1>
 * <p>
 * Kernel class for unloading a model dynamically from the GPU.
 * </p>
 */
public class UnLoadKernel {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UnLoadKernel.class);

    /**
     * {@link #UnLoadKernel()}
     * 0-parameter constructor.
     */
    public UnLoadKernel() {
    }

    /**
     * {@link #unloadModelKernel(byte[])}
     * Unloads a model with kernel api.
     *
     * @return boolean - returns true if the model was successfully unloaded.
     */
    boolean unloadModelKernel(byte[] modelData) {
        boolean success = false;
        try {
            final byte[] finalModelData = modelData; // Make modelData effectively final

            TaskGraph taskGraph = new TaskGraph("s0")
                    .transferToDevice(DataTransferMode.FIRST_EXECUTION, finalModelData)
                    .task("unloadModel", () -> {
                        KernelContext context = new KernelContext();
                        int idx = context.globalIdx;
                        if (idx < finalModelData.length) {
                            finalModelData[idx] = 0; // Simulate unloading by setting data to zero
                        }
                    })
                    .transferToHost(DataTransferMode.EVERY_EXECUTION, finalModelData);

            // Create an immutable task-graph
            ImmutableTaskGraph immutableTaskGraph = taskGraph.snapshot();

            try (TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph)) {
                executionPlan.getDevice(0).getAvailableProcessors();
                executionPlan.withDynamicReconfiguration(Policy.PERFORMANCE, DRMode.PARALLEL).execute();
                success = true;
            } catch (TornadoExecutionPlanException unloadModelKernelExc) {
                log.error("Error executing Tornado plan: {}", unloadModelKernelExc.getMessage());
            }
        } catch (Exception unloadModelKernelExc) {
            log.error("Error unloading model: {}", unloadModelKernelExc.getMessage());
        }

        return success;
    }

    /**
     * {@link #kernelIsUnloaded()}
     * Checks if the kernel is unloaded.
     *
     * @return boolean - returns true if the kernel is unloaded.
     */
    boolean kernelIsUnloaded() {
        //todo: implement this method to check if the kernel is unloaded
        return true;
    }
}
