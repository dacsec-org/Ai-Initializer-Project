package org.dacss.projectinitai.checksums;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link ChecksumsIface}</h1>
 */
@FunctionalInterface
public interface ChecksumsIface {
    Flux<Object> calculateChecksum(ChecksumActions action, String filePath, String expectedChecksum);
}
