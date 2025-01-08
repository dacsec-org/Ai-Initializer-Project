package org.dacss.projectinitai.advisers.processors;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * <h1>{@link VectorizationProcessor}</h1>
 * Pre-processor to vectorize data.
 */
public class VectorizationProcessor implements ProcessingAdviserIface<byte[]> {

    @Override
    public byte[] process(byte[] inputOutput) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(inputOutput);
            return Base64.getEncoder().encode(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * Converts the byte array to a Base64 encoded string.
     * @param data the byte array to be converted.
     * @return the Base64 encoded string.
     */
    public String toBase64String(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
