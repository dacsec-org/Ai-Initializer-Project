package org.dacss.projectinitai.services;
/**/

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.checksums.ChecksumsIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import static org.dacss.projectinitai.checksums.utillities.ChecksumGeneratorUtil.generateSHA512;
import static org.dacss.projectinitai.checksums.utillities.ChecksumVerifierUtil.*;

/**
 * <h1>{@link ChecksumsService}</h1>
 * Hilla back-end service class for the Checksums module.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class ChecksumsService implements ChecksumsIface {

    private static final Logger log = LoggerFactory.getLogger(ChecksumsService.class);

    /**
     * <h2>{@link #ChecksumsService()}</h2>
     * 0-argument constructor.
     */

    public ChecksumsService() {}

    /**
     * <h2>{@link ChecksumsIface#calculateChecksum(String, String, String)}</h2>
     * Handles the calculation of the checksum.
     *
     * @param action The action to perform.
     * @param filePath The path to the file.
     * @param expectedChecksum The expected checksum.
     */
    @Override
    public void calculateChecksum(String action, String filePath, String expectedChecksum) {
        try {
            switch (action) {
                case "verify":
                    verifyFileChecksum(filePath, expectedChecksum);
                    break;
                case "verify_byte_array":
                    verifyByteArrayChecksum(filePath.getBytes(), expectedChecksum);
                    break;
                case "generate":
                    generateSHA512(filePath);
                default:
                    throw new IllegalArgumentException(MessageFormat.format("Invalid action: {0}", action));
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Error calculating checksum: {}", e.getMessage());
        }
    }
}
