package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.loaders.ChecksumVerifier;
import org.dacss.projectinitai.loaders.ModelDirectoryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@BrowserCallable
@AnonymousAllowed
public class HuggingFaceDownloaderService {

    @Autowired
    public HuggingFaceDownloaderService(ModelDirectoryHandler modelDirectoryHandler) {
    }

    public String getApiToken() {
        return CredentialService.getApiToken();
    }

    public boolean downloadModel(String modelId, String expectedChecksum) {
        try {
            String apiToken = getApiToken();
            URI uri = URI.create("https://huggingface.co/api/models/" + modelId + "/download");
            URL url = uri.toURL();
            File modelsDir = ModelDirectoryHandler.createDirectory("models");
            File modelDir = ModelDirectoryHandler.createDirectory(modelsDir.getPath() + "/" + modelId);
            File modelFile = ModelDirectoryHandler.createFile(modelDir.getPath(), modelId + ".zip");

            ModelDirectoryHandler.downloadFile(url.toString(), modelFile);

            boolean isChecksumValid = ChecksumVerifier.verifyChecksum(modelFile.getPath(), expectedChecksum);

            if (isChecksumValid) {
                File checksumsDir = ModelDirectoryHandler.createDirectory(modelsDir.getPath() + "/checksums");
                File checksumFile = ModelDirectoryHandler.createFile(checksumsDir.getPath(), modelId + ".checksum");
                ModelDirectoryHandler.writeFile(checksumFile, expectedChecksum);
            }

            return isChecksumValid;
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Error downloading model: {}", e.getMessage());
            return false;
        }
    }
}
