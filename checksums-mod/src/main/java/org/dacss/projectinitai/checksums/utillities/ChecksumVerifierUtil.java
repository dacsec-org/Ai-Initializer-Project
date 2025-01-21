package org.dacss.projectinitai.checksums.utillities;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <h1>{@link ChecksumVerifierUtil}</h1>
 * Verifies the checksum of a file or byte array.
 */
public class ChecksumVerifierUtil {

    /**
     * Verifies the checksum of a file.
     * @param filePath the path to the file
     * @param expectedChecksum the expected checksum
     * @return true if the checksum is correct, false otherwise
     * @throws IOException if an I/O error occurs
     * @throws NoSuchAlgorithmException if the algorithm is not available
     */
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

    /**
     * Verifies the checksum of a byte array.
     * @param data the byte array
     * @param expectedChecksum the expected checksum
     * @return true if the checksum is correct, false otherwise
     * @throws NoSuchAlgorithmException if the algorithm is not available
     */
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

}
