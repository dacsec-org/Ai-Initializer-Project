package org.dacss.projectinitai.downloaders;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.security.SecurityApiTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DownloadersIface}</h1>
 * Interface for handling download actions.
 * This interface defines a method to process various download actions and return the result as a {@link Flux}.
 * It is annotated with {@link Endpoint}, {@link Component}, {@link BrowserCallable}, and {@link AnonymousAllowed}
 * to indicate that it is a Spring-managed component and can be called from the browser.
 */
@Endpoint
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface DownloadersIface {

    Logger log = LoggerFactory.getLogger(DownloadersIface.class);
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    /**
     * <h3>{@link #processDownloadAction(DownloadAction)}</h3>
     * Processes the download action based on the provided {@link DownloadAction}.
     * This method uses a switch statement to determine the appropriate action to take.
     * If an error occurs during the processing, it logs the error and returns a {@link Flux} with the error.
     * Finally, it logs the completion of the action.
     *
     * @param action the download action to process
     * @return a {@link Flux} that emits the result of the download action
     */
    static Flux<Object> processDownloadAction(DownloadAction action) {
        Flux<Object> result;
        try {
            result = switch (action) {
                case API_TOKEN -> Flux.just(SecurityApiTokenUtil.getApiToken());
                case DOWNLOAD_LLM_JSON -> LLMLibraryUtil.downloadLLMJsonFile();
                case DOWNLOAD_LLM_MODEL -> LLMDownloader.downloadLLMModel();
            };
        } catch (Exception downloadExc) {
            log.error(RED + "Error handling download operation: {}" + RESET, action, downloadExc);
            return Flux.error(new RuntimeException("Error handling download operation: " + action, downloadExc));
        } finally {
            log.info(GREEN + "DownloadersIface action completed: {}" + RESET, action);
        }
        return result;
    }

    /**
     * <h3>{@link #download(DownloadAction)}</h3>
     * Downloads the specified action.
     * This method is intended to be implemented by classes that handle specific download actions.
     *
     * @param action the download action to perform
     * @return a {@link Flux} that emits the result of the download action
     */
    Flux<Object> download(DownloadAction action);
}
