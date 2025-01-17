package org.dacss.projectinitai.downloaders.services;
/**/
import org.dacss.projectinitai.directories.handlers.DirFileHandler;
import org.dacss.projectinitai.security.services.CredentialService;
/**/
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * <h1>{@link HuggingFaceDownloaderService}</h1>
 * <p>
 *     Backend service for downloading models from the Hugging Face model hub.
 * </p>
 * Module dependencies:
 * <ul>
 *     <li>directories-mod{@link DirFileHandler}</li>
 *     <li>security-mod{@link CredentialService}</li>
 * </ul>
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class HuggingFaceDownloaderService {

    private static final Logger log = LoggerFactory.getLogger(HuggingFaceDownloaderService.class);
    private final DirFileHandler dirFileHandler;

    /**
     * {@link #HuggingFaceDownloaderService(DirFileHandler)}
     * <p>
     *     1-parameter constructor.
     * </p>
     */
    @Autowired
    public HuggingFaceDownloaderService(DirFileHandler dirFileHandler) {
        this.dirFileHandler = dirFileHandler;
    }

    /**
     * {@link #getApiToken()}
     *
     * @return String - returns the API token.
     */
    public String getApiToken() {
        return CredentialService.getApiToken();
    }

    /**
     * {@link #downloadModel(String)}
     *
     * @param modelId - the model id.
     * @return boolean - returns true if the model was downloaded successfully.
     */
    public boolean downloadModel(String modelId) {
        try {
            String apiToken = getApiToken();
            URI uri = URI.create(String.format("https://huggingface.co/api/models/%s/download", modelId));
            URL url = uri.toURL();
            String modelsDirPath = "models";
            dirFileHandler.createDirectory(modelsDirPath);
            String modelDirPath = String.format("%s/%s", modelsDirPath, modelId);
            dirFileHandler.createDirectory(modelDirPath);
            String modelFilePath = String.format("%s/%s.zip", modelDirPath, modelId);
            dirFileHandler.createFile(modelDirPath, String.format("%s.zip", modelId));

            try (var inputStream = url.openStream()) {
                Files.copy(inputStream, new File(modelFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            return true;
        } catch (IOException downloadExc) {
            log.error("Error downloading model: {}", downloadExc.getMessage());
            return false;
        }
    }
}
