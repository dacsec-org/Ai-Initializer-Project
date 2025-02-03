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
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    static Flux<Object> processChecksumAction(ChecksumActions action) {
        Flux<Object> result;
        try {
            result = switch (action) {
                case VERIFY -> Flux.just("Verify checksum");
                case GENERATE -> Flux.just("Generate checksum");
                case VERIFY_BYTE_ARRAY -> Flux.just("Verify byte array checksum");
            };
        } catch (Exception checksumExc) {
            log.error(RED + "Error from ChecksumsIface performing action: {}" + RESET, action, checksumExc);
            return Flux.error(checksumExc);
        } finally {
            log.info(GREEN + "ChecksumsIface action completed: {}" + RESET, action);
        }
        return result;
    }

    Flux<Object> calculateChecksum(ChecksumActions action);
}
