package org.dacss.projectinitai.checksums;

import reactor.core.publisher.Flux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link ChecksumsIface}</h1>
 */
@FunctionalInterface
public interface ChecksumsIface {

    Flux<Object> calculateChecksum(ChecksumActions action);
}
