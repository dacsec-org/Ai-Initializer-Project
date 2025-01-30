package org.dacss.projectinitai.downloaders.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * <h1>{@link LLMLibraryUtil}</h1>
 * Utility class for downloading LLM JSON file in weekly intervals.
 */
public class LLMLibraryUtil {

    private static final Logger log = LoggerFactory.getLogger(LLMLibraryUtil.class);

    public Flux<Object> downloadLLMJsonFile() {
        return Flux.create(sink -> {
            String urlString = "https://huggingface.co/api/models";
            log.info("Downloading JSON from URL: {}", urlString);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    Files.writeString(Paths.get("llm.json"),
                            response.body());
                    sink.next(response.body());
                    sink.complete();
                } else {
                    sink.error(new IOException("HTTP request failed with response code " + response.statusCode()));
                }
            } catch (IOException | InterruptedException e) {
                sink.error(e);
            }
        }).delayElements(Duration.ofMillis(100));
    }
}
