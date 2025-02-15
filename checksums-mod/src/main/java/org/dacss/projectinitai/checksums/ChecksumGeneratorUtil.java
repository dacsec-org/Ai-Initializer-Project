package org.dacss.projectinitai.checksums;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <h1>{@link ChecksumGeneratorUtil}</h1>
 * Generates checksums for a given file using various algorithms.
 */
public class ChecksumGeneratorUtil {

    /**
     * Generates a checksum for the given file using the specified algorithm.
     *
     * @param filePath  the path to the file
     * @param algorithm the algorithm to use (e.g., "SHA-256", "SHA-512")
     * @return the checksum as a hexadecimal string
     * @throws IOException              if an I/O error occurs
     * @throws NoSuchAlgorithmException if the specified algorithm is not available
     */
    public static String generateChecksum(String filePath, String algorithm) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        StringBuilder hash = new StringBuilder();
        for (byte b : hashBytes) {
            hash.append(String.format("%02x", b));
        }
        return hash.toString();
    }

    /**
     * Generates an SHA-256 checksum for the given file.
     *
     * @param filePath the path to the file
     * @return the SHA-256 checksum as a hexadecimal string
     * @throws IOException              if an I/O error occurs
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    public static String generateSHA256(String filePath) throws IOException, NoSuchAlgorithmException {
        return generateChecksum(filePath, "SHA-256");
    }

    /**
     * Generates an SHA-512 checksum for the given file.
     *
     * @param filePath the path to the file
     * @return the SHA-512 checksum as a hexadecimal string
     * @throws IOException              if an I/O error occurs
     * @throws NoSuchAlgorithmException if the SHA-512 algorithm is not available
     */
    public static String generateSHA512(String filePath) throws IOException, NoSuchAlgorithmException {
        return generateChecksum(filePath, "SHA-512");
    }
}
