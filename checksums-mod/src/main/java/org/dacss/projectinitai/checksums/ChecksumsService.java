package org.dacss.projectinitai.checksums;
/**/

import com.vaadin.hilla.BrowserCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ChecksumsService}</h1>
 * Service class for the Checksums module.
 */
@Service
@BrowserCallable
public class ChecksumsService {

    private final ChecksumHandler handler;

    /**
     * <h2>{@link #ChecksumsService(ChecksumHandler)}</h2>
     *
     * @param handler {@link ChecksumHandler}
     */
    @Autowired
    public ChecksumsService(ChecksumHandler handler) {
        this.handler = handler;
    }

    /**
     * <h2>{@link ChecksumsService#handleChecksumAction(String, String, String)}</h2>
     *
     * @param action           The action to be performed.
     * @param filePath         The path to the file.
     * @param expectedChecksum The expected checksum.
     * @return The result of the action.
     */
    public Object handleChecksumAction(String action, String filePath, String expectedChecksum) {
        return switch (action.toLowerCase()) {
            case "verify" ->
                    handler.verifyChecksum(filePath, expectedChecksum);
            case "generate" -> handler.generateChecksum(filePath);
            default ->
                    throw new IllegalArgumentException(STR."Unknown action: \{action}");
        };
    }
}
