package org.dacss.projectinitai.loaders.handlers;

import org.dacss.projectinitai.loaders.kernels.DynamicModelLoaderKernel;
import org.dacss.projectinitai.loaders.kernels.DynamicModelUnLoaderKernel;
import org.springframework.stereotype.Component;

@Component
public class ModelLoadUnloadHandler {

    private final DynamicModelLoaderKernel loaderKernel;
    private final DynamicModelUnLoaderKernel unloaderKernel;

    public ModelLoadUnloadHandler(DynamicModelLoaderKernel loaderKernel, DynamicModelUnLoaderKernel unloaderKernel) {
        this.loaderKernel = loaderKernel;
        this.unloaderKernel = unloaderKernel;
    }

    public byte[] loadModel(String modelPath) {
        return loaderKernel.loadModelKernel(modelPath);
    }

    public boolean unloadModel(byte[] modelData) {
        return unloaderKernel.unloadModel(modelData);
    }
}
