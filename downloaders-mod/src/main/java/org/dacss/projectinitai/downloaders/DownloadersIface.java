package org.dacss.projectinitai.downloaders;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DownloadersIface}</h1>
 */
@FunctionalInterface
public interface DownloadersIface {

    Flux<Object> download(DownloadAction action, String llmName);
}
