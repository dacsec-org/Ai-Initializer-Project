package org.dacss.projectinitai.loaders;

import org.dacss.projectinitai.loaders.LoadKernel;
import org.dacss.projectinitai.loaders.LoadUnloadService;
import org.dacss.projectinitai.loaders.LoadersIface;
import org.dacss.projectinitai.loaders.UnLoadKernel;

import org.springframework.stereotype.Component;

/**
 * <h1>{@link LoadUnloadHandler}</h1>
 * Handler class for loading and unloading models.
 */
@Component
public class LoadUnloadHandler implements LoadersIface {

    private final LoadUnloadService loadUnloadService;
    private final LoadKernel loaderKernel;
    private final UnLoadKernel unloaderKernel;

    /**
     * <h2>{@link #LoadUnloadHandler()}</h2>
     * 0-arg constructor to instantiate the {@link LoadUnloadService}, {@link LoadKernel} and {@link UnLoadKernel}.
     */
    public LoadUnloadHandler() {
        this.loadUnloadService = new LoadUnloadService();
        this.loaderKernel = new LoadKernel();
        this.unloaderKernel = new UnLoadKernel();
    }

    /**
     * <h2>{@link #loadModel(String)}</h2>
     *
     * @param modelPath The path to the model.
     * @return The loaded model.
     */
    public byte[] loadModel(String modelPath) {
        return loaderKernel.loadModelKernel(modelPath);
    }

    /**
     * <h2>{@link #unloadModel(byte[])}</h2>
     *
     * @param modelData The model data to unload.
     * @return A message indicating the result of the unload operation.
     */
    public String unloadModel(byte[] modelData) {
        unloaderKernel.unloadModelKernel(modelData);
        return "Model unloaded successfully";
    }

    /**
     * <h2>{@link #processModels()}</h2>
     * Method to process models.
     */
    @Override
    public void processModels() {
        //todo: implement
    }
}
