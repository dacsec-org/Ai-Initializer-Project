package org.dacss.projectinitai.loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChecksumVerifier {
    public static boolean verifyChecksum(String filePath, String expectedChecksum) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString().equals(expectedChecksum);
    }

    public static boolean verifyChecksum(byte[] data, String expectedChecksum) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(data);
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString().equals(expectedChecksum);
    }

    public static byte[] loadModelWithChecksum(String modelPath, String expectedChecksum) throws IOException, NoSuchAlgorithmException {
        if (verifyChecksum(modelPath, expectedChecksum)) {
            return ModelDirectoryHandler.loadModel(modelPath);
        } else {
            throw new IOException("Checksum verification failed");
        }
    }
}
