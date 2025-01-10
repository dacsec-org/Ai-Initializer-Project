package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.loader.ChecksumVerifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@BrowserCallable
@AnonymousAllowed
public class HuggingFaceService {

    public String getApiToken() {
        return CredentialService.getApiToken();
    }

    public boolean downloadModel(String modelId, String expectedChecksum) {
        try {
            String apiToken = getApiToken();
            URI uri = URI.create("https://huggingface.co/api/models/" + modelId + "/download");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + apiToken);

            File modelsDir = new File("models");
            if (!modelsDir.exists() && !modelsDir.mkdir()) {
                throw new IOException("Failed to create models directory");
            }

            File modelDir = new File(modelsDir, modelId);
            if (!modelDir.exists() && !modelDir.mkdir()) {
                throw new IOException("Failed to create model directory");
            }

            File modelFile = new File(modelDir, modelId + ".zip");
            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(modelFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            boolean isChecksumValid = ChecksumVerifier.verifyChecksum(modelFile.getPath(), expectedChecksum);

            if (isChecksumValid) {
                File checksumsDir = new File(modelsDir, "checksums");
                if (!checksumsDir.exists() && !checksumsDir.mkdir()) {
                    throw new IOException("Failed to create checksums directory");
                }

                File checksumFile = new File(checksumsDir, modelId + ".checksum");
                try (FileWriter writer = new FileWriter(checksumFile)) {
                    writer.write(expectedChecksum);
                }
            }

            return isChecksumValid;
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Error downloading model: {}", e.getMessage());
            return false;
        }
    }
}
