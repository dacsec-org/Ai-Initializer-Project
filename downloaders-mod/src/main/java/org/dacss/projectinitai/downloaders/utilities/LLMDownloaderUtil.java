package org.dacss.projectinitai.downloaders.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link LLMDownloaderUtil}</h1>
 * Utility class for downloading LLM model.
 */
@Component
public class LLMDownloaderUtil {

    private static final Logger log = LoggerFactory.getLogger(LLMDownloaderUtil.class);
    private static String baseUrl;
    private String modelId;

    public static Flux<Object> downloadLLMModel() {
        try {
            baseUrl = "https://huggingface.co/models";
            String modelId = "";
            String finalUrl = baseUrl + "/" + modelId;
            log.info("Final URL: {}", finalUrl);
            return Flux.just(finalUrl);
        } catch (Exception e) {
            log.error("Error downloading LLM model", e);
        }
        return Flux.generate(sink -> {
            sink.complete();
            log.error("\033[31m llmDownloaderUtil Error downloading LLM model\033[0m");
        });
    }
}
