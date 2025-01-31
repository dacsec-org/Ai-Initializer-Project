package org.dacss.projectinitai.security;

import java.io.IOException;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link SecurityIface}</h1>
 */
@FunctionalInterface
public interface SecurityIface {
    /**
     * <h2>{@link #secure()}</h2>
     */
    Flux<Object> secure(SecurityActions action) throws IOException;
}
