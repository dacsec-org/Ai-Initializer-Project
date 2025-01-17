package org.dacss.projectinitai.downloaders.services;
/**/
import org.dacss.projectinitai.checksums.ChecksumVerifier;
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
import java.security.NoSuchAlgorithmException;

/**
 * <h1>{@link HuggingFaceDownloaderService}</h1>
 * <p>
 *     Backend service for downloading models from the Hugging Face model hub.
 * </p>
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class HuggingFaceDownloaderService {

    private static final Logger log = LoggerFactory.getLogger(HuggingFaceDownloaderService.class);

    /**
     * {@link #HuggingFaceDownloaderService(ModelDirectoryHandler)} 1-parameter constructor.
     */
    @Autowired
    public HuggingFaceDownloaderService(ModelDirectoryHandler modelDirectoryHandler) {
        //FIXME: ASAP ModelDirectoryHandler should was somehow lost in the process of refactoring
    }

    /**
     * {@link #getApiToken()} method.
     *
     * @return String - returns the API token.
     */
    public String getApiToken() {
        return CredentialService.getApiToken();
    }

    /**
     * {@link #downloadModel(String, String)} method.
     *
     * @param modelId - the model id.
     * @param expectedChecksum - the expected checksum.
     * @return boolean - returns true if the model was downloaded successfully.
     */
    public boolean downloadModel(String modelId, String expectedChecksum) {
        try {
            String apiToken = getApiToken();
            URI uri = URI.create(STR."https://huggingface.co/api/models/\{modelId}/download");
            URL url = uri.toURL();
            File modelsDir = ModelDirectoryHandler.createDirectory("models");
            File modelDir = ModelDirectoryHandler.createDirectory(STR."\{modelsDir.getPath()}/\{modelId}");
            File modelFile = ModelDirectoryHandler.createFile(modelDir.getPath(), STR."\{modelId}.zip");

            ModelDirectoryHandler.downloadFile(url.toString(), modelFile);

            boolean isChecksumValid = ChecksumVerifier.verifyChecksum(modelFile.getPath(), expectedChecksum);

            if (isChecksumValid) {
                File checksumsDir = ModelDirectoryHandler.createDirectory(STR."\{modelsDir.getPath()}/checksums");
                File checksumFile = ModelDirectoryHandler.createFile(checksumsDir.getPath(), STR."\{modelId}.checksum");
                ModelDirectoryHandler.writeFile(checksumFile, expectedChecksum);
            }

            return isChecksumValid;
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Error downloading model: {}", e.getMessage());
            return false;
        }
    }
}
