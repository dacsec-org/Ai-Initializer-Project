package org.dacss.projectinitai.downloaders.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link LLMDownloaderUtil}</h1>
 * Utility class for downloading LLM model.
 */
public class LLMDownloaderUtil {

    private static final Logger log = LoggerFactory.getLogger(LLMDownloaderUtil.class);
    private String baseUrl;

    public Flux<Object> downloadLLMModel() {
        try {
            baseUrl = "https://huggingface.co/models";
            String finalUrl = baseUrl;
            log.info("Final URL: {}", finalUrl);
            return Flux.just(finalUrl);
        } catch (Exception e) {
            log.error("Error downloading LLM model", e);
        }
        return Flux.generate(sink -> {
            sink.complete();
            log.error("Error downloading LLM model");
        });
    }
}
