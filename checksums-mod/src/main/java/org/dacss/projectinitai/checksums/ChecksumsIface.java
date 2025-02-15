package org.dacss.projectinitai.checksums;

import reactor.core.publisher.Flux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link ChecksumsIface}</h1>
 */
@FunctionalInterface
public interface ChecksumsIface {

    Logger log = LoggerFactory.getLogger(ChecksumsIface.class);

    static Flux<Object> processChecksumAction(ChecksumActions action) {
        Flux<Object> result;
        try {
            result = switch (action) {
                case SHA256 -> null;
                case SHA512 -> null;
                case VERIFY -> Flux.just("Verify checksum");
                case GENERATE -> Flux.just("Generate checksum");
                case VERIFY_BYTE_ARRAY -> Flux.just("Verify byte array checksum");
            };
        } catch (Exception checksumExc) {
            log.error("{}: ", "ChecksumsIface action failed" + checksumExc.getMessage());
            return Flux.error(checksumExc);
        } finally {
            log.info("{}: ", "ChecksumsIface action completed");
        }
        return result;
    }

    Flux<Object> calculateChecksum(ChecksumActions action);
}
