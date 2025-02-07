package org.dacss.projectinitai.downloaders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * <h1>{@link SearchModels}</h1>
 */
public class SearchModels {

    private static final Logger log = LoggerFactory.getLogger(SearchModels.class);
    private static final String BASE_URL = "https://huggingface.co/api/models?search=";

    /**
     * <h3>{@link #SearchModels()}</h3>
     * Default 0-arg constructor.
     */
    public SearchModels() {
    }

    public static Flux<Object> searchModels(DownloadAction action, String query) {
        if (action != DownloadAction.SEARCH) {
            return Flux.error(new UnsupportedOperationException("Unsupported action: " + action));
        }

        String searchUrl = BASE_URL + query;
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchUrl))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        return Flux.create(sink -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> {
                log.info("HTTP Status Code: {}", response.statusCode());
                log.info("Response Body: {}", response.body());
                return response.body();
            })
            .thenAccept(responseBody -> {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    JsonNode models = objectMapper.readTree(responseBody);
                    sink.next(models);
                    sink.complete();
                } catch (IOException e) {
                    sink.error(new IOException("Failed to parse models: " + e.getMessage(), e));
                }
            })
            .exceptionally(e -> {
                log.error("Error occurred while searching models: ", e);
                sink.error(e);
                return null;
            }));
    }
}
