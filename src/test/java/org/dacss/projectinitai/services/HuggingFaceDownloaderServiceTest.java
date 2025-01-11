package org.dacss.projectinitai.services;

import org.dacss.projectinitai.loader.ChecksumVerifier;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class HuggingFaceDownloaderServiceTest {

    @Test
    void downloadModel() throws IOException, NoSuchAlgorithmException {
        // Mock the CredentialService
        CredentialService credentialService = Mockito.mock(CredentialService.class);
        Mockito.when(CredentialService.getApiToken()).thenReturn("mocked-api-token");

        // Mock the HttpURLConnection
        HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
        InputStream inputStream = Mockito.mock(InputStream.class);
        when(connection.getInputStream()).thenReturn(inputStream);

        // Mock the URL
        URL url = Mockito.mock(URL.class);
        when(url.openConnection()).thenReturn(connection);

        // Mock the ChecksumVerifier
        ChecksumVerifier checksumVerifier = Mockito.mock(ChecksumVerifier.class);
        when(ChecksumVerifier.verifyChecksum(anyString(), anyString())).thenReturn(true);

        // Create an instance of HuggingFaceDownloaderService
        HuggingFaceDownloaderService huggingFaceDownloaderService = new HuggingFaceDownloaderService();

        // Verify the model download
        assertTrue(huggingFaceDownloaderService.downloadModel("mocked-model-id", "mocked-checksum"));
    }
}
