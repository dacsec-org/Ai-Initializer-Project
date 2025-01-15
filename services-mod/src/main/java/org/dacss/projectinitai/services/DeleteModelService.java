package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>{@link DeleteModelService}</h1>
 * Service class for deleting local LLM models with a frontend view 'src/main/frontend/views/delete-model.tsx'.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class DeleteModelService {

    /**
     * Deletes a local LLM model.
     * @param modelPath The path of the model to be deleted.
     * @return A message indicating the result of the operation.
     */
    public String deleteModel(String modelPath) {
        Path path = Paths.get(modelPath);

        try {
            // Ensure the path exists
            if (!Files.exists(path)) {
                return "Model path does not exist.";
            }

            // Delete the model
            Files.delete(path);
            return "Model deleted successfully.";
        } catch (IOException e) {
            return "Error deleting model: " + e.getMessage();
        }
    }
}
