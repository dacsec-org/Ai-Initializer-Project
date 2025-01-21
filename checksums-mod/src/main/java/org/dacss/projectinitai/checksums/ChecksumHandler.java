package org.dacss.projectinitai.checksums;
/**/
import org.dacss.projectinitai.checksums.utillities.ChecksumVerifierUtil;
import org.dacss.projectinitai.checksums.utillities.ChecksumGeneratorUtil;
/**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * <h1>{@link ChecksumHandler}</h1>
 * Handler class for checksum actions.
 */
@Component
public class ChecksumHandler implements ChecksumsIface {

    private static final Logger log = LoggerFactory.getLogger(ChecksumHandler.class);

    /**
     * <h2>{@link ChecksumHandler#verifyChecksum()}</h2>
     *
     * @param filePath         The path to the file.
     * @param expectedChecksum The expected checksum.
     * @return true if the checksum is correct, false otherwise.
     */
    public boolean verifyChecksum(String filePath, String expectedChecksum) {
        log.info("Verifying checksum for file: {}", filePath);
        try {
            return ChecksumVerifierUtil.verifyChecksum(filePath, expectedChecksum);
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Error verifying checksum", e);
            return false;
        }
    }

    /**
     * <h2>{@link ChecksumHandler#generateChecksum()}</h2>
     *
     * @param filePath The path to the file.
     * @return The generated checksum.
     */
    public String generateChecksum(String filePath) {
        log.info("Generating checksum for file: {}", filePath);
        try {
            return ChecksumGeneratorUtil.generateSHA512(filePath);
        } catch (IOException e) {
            log.error("Error generating checksum", e);
            return null;
        }
    }

    /**
     * <h2>{@link ChecksumsIface#calculateChecksum()}</h2>
     */
    @Override
    public void calculateChecksum() {
        //todo: implement
        log.info("Calculating checksum");
    }
}
