package org.dacss.projectinitai.loaders;

import org.springframework.stereotype.Component;

@Component
public class ModelLoadUnloadHandler {

    private DynamicModelLoaderKernel loaderKernel;
    private DynamicModelUnLoaderKernel unloaderKernel;

    public ModelLoadUnloadHandler() {

    }

    public byte[] loadModel(String modelPath) {
        return loaderKernel.loadModelKernel(modelPath);
    }

    public boolean unloadModel(byte[] modelData) {
        return unloaderKernel.unloadModel(modelData);
    }
}
