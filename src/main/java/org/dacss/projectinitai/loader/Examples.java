//package org.dacss.projectinitai.loader;
//
//import lombok.With;
//import org.checkerframework.common.returnsreceiver.qual.This;
//import uk.ac.manchester.tornado.api.*;
//import uk.ac.manchester.tornado.api.annotations.Parallel;
//import uk.ac.manchester.tornado.api.enums.DataTransferMode;
//import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;
//import uk.ac.manchester.tornado.api.types.matrix.Matrix2DFloat;
//
//public class Compute {
//    private static void mxmLoop(Matrix2DFloat A, Matrix2DFloat B, Matrix2DFloat C, final int size) {
//        for (@Parallel int i = 0; i < size; i++) {
//            for (@Parallel int j = 0; j < size; j++) {
//                float sum = 0.0f;
//                for (int k = 0; k < size; k++) {
//                    sum += A.get(i, k) * B.get(k, j);
//                }
//                C.set(i, j, sum);
//            }
//        }
//    }
//
//    public void run(Matrix2DFloat A, Matrix2DFloat B, Matrix2DFloat C, final int size) {
//
//        // Create a task-graph with multiple tasks. Each task points to an exising Java method
//        // that can be accelerated on a GPU/FPGA
//        TaskGraph taskGraph = new TaskGraph("myCompute")
//                .transferToDevice(DataTransferMode.FIRST_EXECUTION, A, B) // Transfer data from host to device only in the first execution
//                .task("mxm", Compute::mxmLoop, A, B, C, size)             // Each task points to an existing Java method
//                .transferToHost(DataTransferMode.EVERY_EXECUTION, C);     // Transfer data from device to host
//
//        // Create an immutable task-graph
//        ImmutableTaskGraph immutableTaskGraph = taskGraph.snaphot();
//
//        // Create an execution plan from an immutable task-graph
//        try (TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph)) {
//
//            // Run the execution plan on the default device
//            TorandoExecutionResult executionResult = executionPlan.execute();
//
//        } catch (TornadoExecutionPlanException e) {
//            // handle exception
//            // ...
//        }
//    }
//}
//
////b) DynamicModelLoaderKernel API
////
////Another way to express compute-kernels in TornadoVM is via the DynamicModelLoaderKernel API. To do so, TornadoVM exposes the KernelContext data structure
//// , in which the application can directly access the thread-id, allocate memory in local memory (shared memory on NVIDIA devices),
//// and insert barriers. This model is similar to programming compute-kernels in SYCL, oneAPI, OpenCL and CUDA. Therefore, this API is
//// more suitable for GPU/FPGA expert programmers that want more control or want to port existing CUDA/OpenCL compute kernels into TornadoVM.
////
////The following code-snippet shows the Matrix Multiplication example using the kernel-parallel API:
//
//public class Compute {
//    private static void mxmKernel(KernelContext context, Matrix2DFloat A, Matrix2DFloat B, Matrix2DFloat C, final int size) {
//        int idx = context.globalIdx
//        int jdx = context.globalIdy;
//        float sum = 0;
//        for (int k = 0; k < size; k++) {
//            sum += A.get(idx, k) * B.get(k, jdx);
//        }
//        C.set(idx, jdx, sum);
//    }
//
//    public void run(Matrix2DFloat A, Matrix2DFloat B, Matrix2DFloat C, final int size) {
//        // When using the kernel-parallel API, we need to create a Grid and a Worker
//        WorkerGrid workerGrid = new WorkerGrid2D(size, size);    // Create a 2D Worker
//        GridScheduler gridScheduler = new GridScheduler("myCompute.mxm", workerGrid);  // Attach the worker to the Grid
//        KernelContext context = new KernelContext();             // Create a context
//        workerGrid.setLocalWork(16, 16, 1);                      // Set the local-group size
//
//        TaskGraph taskGraph = new TaskGraph("myCompute")
//                .transferToDevice(DataTransferMode.FIRST_EXECUTION, A, B) // Transfer data from host to device only in the first execution
//                .task("mxm", Compute::mxmKernel, context, A, B, C, size)   // Each task points to an existing Java method
//                .transferToHost(DataTransferMode.EVERY_EXECUTION, C);     // Transfer data from device to host
//
//        // Create an immutable task-graph
//        ImmutableTaskGraph immutableTaskGraph = taskGraph.snapshot();
//
//        // Create an execution plan from an immutable task-graph
//        try (TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph)) {
//            // Run the execution plan on the default device
//            // Execute the execution plan
//            TorandoExecutionResult executionResult = executionPlan
//                    .withGridScheduler(gridScheduler)
//                    .execute();
//        } catch (TornadoExecutionPlanException e) {
//            // handle exception
//            // ...
//        }
//    }
//}
//
////Additionally, the two modes of expressing parallelism (kernel and loop parallelization) can be combined in the same task graph object.
////        4. Dynamic Reconfiguration
////Dynamic reconfiguration is the ability of TornadoVM to perform live task migration between devices, which means that
//// TornadoVM decides where to execute the code to increase performance (if possible). In other words, TornadoVM switches
//// devices if it can detect that a specific device can yield better performance (compared to another).
////
////With the task-migration, the TornadoVM's approach is to only switch device if it detects an application can be executed
//// faster than the CPU execution using the code compiled by C2 or Graal-JIT, otherwise it will stay on the CPU. So TornadoVM
//// can be seen as a complement to C2 and Graal JIT compilers. This is because there is no single hardware to best execute all
//// workloads efficiently. GPUs are very good at exploiting SIMD applications, and FPGAs are very good at exploiting pipeline applications.
//// If your applications follow those models, TornadoVM will likely select heterogeneous hardware.
//// Otherwise, it will stay on the CPU using the default compilers (C2 or Graal).
////
////To use the dynamic reconfiguration, you can execute using TornadoVM policies. For example:
////
////// TornadoVM will execute the code in the best accelerator.
//        executionPlan.withDynamicReconfiguration(Policy.PERFORMANCE, DRMode.PARALLEL)
//             .execute();
//
