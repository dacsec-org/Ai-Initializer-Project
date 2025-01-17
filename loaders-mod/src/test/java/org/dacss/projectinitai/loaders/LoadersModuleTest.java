package org.dacss.projectinitai.loaders;

import org.dacss.projectinitai.loaders.handlers.ModelLoadUnloadHandler;
import org.dacss.projectinitai.loaders.kernels.DynamicModelLoaderKernel;
import org.dacss.projectinitai.loaders.kernels.DynamicModelUnLoaderKernel;
import org.dacss.projectinitai.loaders.services.ModelLoadUnloadService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * <h1>{@link LoadersModuleTest}</h1>
 * <p>
 * Suite for the loaders-mod.
 * </p>
 * Methods under test:
 * <ul>
 *     <li>{@link #testLoadModel()}</li>
 *     <li>{@link #testUnloadModel()}</li>
 *     <li>{@link #testLoadModelKernel()}</li>
 *     <li>{@link #testUnloadModelKernel()}</li>
 * </ul>
 */
public class LoadersModuleTest {

    private ModelLoadUnloadService modelLoadUnloadService;
    private ModelLoadUnloadHandler modelLoadUnloadHandler;
    private DynamicModelLoaderKernel dynamicModelLoaderKernel;
    private DynamicModelUnLoaderKernel dynamicModelUnLoaderKernel;

    @BeforeMethod
    public void setUp() {
        dynamicModelLoaderKernel = new DynamicModelLoaderKernel();
        dynamicModelUnLoaderKernel = new DynamicModelUnLoaderKernel();
        modelLoadUnloadHandler = new ModelLoadUnloadHandler(dynamicModelLoaderKernel, dynamicModelUnLoaderKernel);
        modelLoadUnloadService = new ModelLoadUnloadService(modelLoadUnloadHandler);
    }

    @AfterSuite
    public void tearDown() {
        // Add any necessary cleanup code here
    }

    @Test
    public void testLoadModel() {
        byte[] modelData = modelLoadUnloadService.loadModel("path/to/model");
        assertNotNull(modelData, "Model data should not be null");
        System.out.println("Test 'loadModel()' passed: Model data loaded");
    }

    @Test(dependsOnMethods = "testLoadModel")
    public void testUnloadModel() {
        byte[] modelData = modelLoadUnloadService.loadModel("path/to/model");
        boolean result = modelLoadUnloadService.unloadModel(modelData);
        assertTrue(result, "Model should be unloaded successfully");
        System.out.println("Test 'unloadModel()' passed: Model unloaded");
    }

    @Test
    public void testLoadModelKernel() throws Exception {
        Method loadModelKernelMethod = DynamicModelLoaderKernel.class.getDeclaredMethod("loadModelKernel", String.class);
        loadModelKernelMethod.setAccessible(true);
        byte[] modelData = (byte[]) loadModelKernelMethod.invoke(dynamicModelLoaderKernel, "path/to/model");
        assertNotNull(modelData, "Model data should not be null");
        System.out.println("Test 'loadModelKernel()' passed: Model data loaded");
    }

    @Test(dependsOnMethods = "testLoadModelKernel")
    public void testUnloadModelKernel() throws Exception {
        Method loadModelKernelMethod = DynamicModelLoaderKernel.class.getDeclaredMethod("loadModelKernel", String.class);
        loadModelKernelMethod.setAccessible(true);
        byte[] modelData = (byte[]) loadModelKernelMethod.invoke(dynamicModelLoaderKernel, "path/to/model");

        Method unloadModelKernelMethod = DynamicModelUnLoaderKernel.class.getDeclaredMethod("unloadModelKernel", byte[].class);
        unloadModelKernelMethod.setAccessible(true);
        boolean result = (boolean) unloadModelKernelMethod.invoke(dynamicModelUnLoaderKernel, modelData);
        assertTrue(result, "Model should be unloaded successfully");
        System.out.println("Test 'unloadModelKernel()' passed: Model unloaded");
    }
}
