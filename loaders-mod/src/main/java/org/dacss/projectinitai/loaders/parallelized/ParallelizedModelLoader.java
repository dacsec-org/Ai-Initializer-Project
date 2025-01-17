//package org.dacss.projectinitai.loaders.parallelized;
///**/
//import org.slf4j.Logger;
//import org.springframework.stereotype.Component;
//import uk.ac.manchester.tornado.api.DRMode;
//import uk.ac.manchester.tornado.api.Policy;
//import uk.ac.manchester.tornado.api.TaskGraph;
//import uk.ac.manchester.tornado.api.TornadoExecutionPlan;
//import uk.ac.manchester.tornado.api.annotations.Parallel;
//import uk.ac.manchester.tornado.api.enums.DataTransferMode;
//import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;
//
//import java.io.IOException;
//
///***
// * <h1>{@link ParallelizedModelLoader}</h1>
// * <p>
// *     This class is responsible for loading the model in parallel using the TornadoVM API.
// * </p>
// */
//@Component
//public class ParallelizedModelLoader {
//
//    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ParallelizedModelLoader.class);
//
//    /**
//     * {@link #ParallelizedModelLoader()} 0-parameter constructor.
//     */
//    public ParallelizedModelLoader() {
//    }
//
//    /**
//     * {@link #loadModelWithParallelLoop()} method.
//     *
//     * @return byte[] - returns the model data.
//     * @throws IOException - throws an IOException.
//     * @throws TornadoExecutionPlanException - throws a TornadoExecutionPlanException.
//     */
//    private byte[] loadModelWithParallelLoop() throws IOException, TornadoExecutionPlanException {
//        byte[] model = ModelDirectoryHandler.loadModel("modelPath");
//        //FIXME: ModelDirectoryHandler was lost in the refactor, we need to find a way to load the model
//        byte[] modelData = ModelDirectoryHandler.loadModel("modelPath");
//
//        TaskGraph taskGraph = new TaskGraph("s0")
//                .transferToDevice(DataTransferMode.FIRST_EXECUTION, modelData)
//                .task("loadModel", () -> {
//                    for (@Parallel int i = 0; i < modelData.length; i++) {
//                        //FIXME: ASAP this should be replaced with a real model loading process
//                        modelData[i] = (byte) (modelData[i] + 1);
//                    }
//                })
//                .transferToHost(DataTransferMode.EVERY_EXECUTION, modelData);
//
//        try (TornadoExecutionPlan executionPlan = new TornadoExecutionPlan()) {
//            executionPlan.getDevice(0).getAvailableProcessors();
//            executionPlan.withDynamicReconfiguration(Policy.PERFORMANCE, DRMode.PARALLEL).execute();
//        }
//
//        return modelData;
//    }
//
//    /**
//     * {@link #process(String)} method.
//     *
//     * @param input - input string.
//     */
//    public void process(String input) {
//        try {
//            loadModelWithParallelLoop();
//        } catch (IOException | TornadoExecutionPlanException processExc) {
//            log.error("Error processing input: {}", processExc.getMessage());
//        }
//    }
//}
