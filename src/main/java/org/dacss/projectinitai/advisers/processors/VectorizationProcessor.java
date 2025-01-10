package org.dacss.projectinitai.advisers.processors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * <h1>{@link VectorizationProcessor}</h1>
 * Pre-processor to vectorize data.
 */
@Slf4j
@Component
public class VectorizationProcessor implements StringProcessingAdviserIface {

    @Override
    public String processString(String stringInputOutput) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(stringInputOutput.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error processing string: ", e);
            return null;
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
