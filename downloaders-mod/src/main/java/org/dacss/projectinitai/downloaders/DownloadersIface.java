package org.dacss.projectinitai.downloaders;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import reactor.core.publisher.Flux;

@Endpoint
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface DownloadersIface {

    Flux<Object> download(String llmName);
}
