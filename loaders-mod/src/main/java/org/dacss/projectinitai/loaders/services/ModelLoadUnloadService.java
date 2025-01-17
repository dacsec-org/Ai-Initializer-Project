package org.dacss.projectinitai.loaders.services;

import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.loaders.ModelLoadUnloadHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@BrowserCallable
public class ModelLoadUnloadService {

    private final ModelLoadUnloadHandler handler;

    @Autowired
    public ModelLoadUnloadService(ModelLoadUnloadHandler handler) {
        this.handler = handler;
    }

    public byte[] loadModel(String modelPath) {
        return handler.loadModel(modelPath);
    }

    public boolean unloadModel(byte[] modelData) {
        return handler.unloadModel(modelData);
    }
}
