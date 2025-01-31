package org.dacss.projectinitai.downloaders;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link DownloadersIface}</h1>
 */
@FunctionalInterface
public interface DownloadersIface {

    Flux<Object> download(DownloadAction action);
}
