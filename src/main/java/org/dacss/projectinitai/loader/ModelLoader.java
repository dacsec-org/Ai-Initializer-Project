package org.dacss.projectinitai.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

///**
// * <h1>{@link ModelLoader}</h1>
// * Class for loading an LLM.
// */
public class ModelLoader {
    public static byte[] loadModel(String filePath) throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }
}
