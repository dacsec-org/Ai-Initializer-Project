package org.dacss.projectinitai.embedding;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link EmbeddingIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface EmbeddingIface {

    Flux<Object> processEmbedding(EmbeddingTypes type);
}
