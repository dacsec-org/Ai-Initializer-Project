package org.dacss.projectinitai.downloaders;

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
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * <h1>{@link LLMLibraryUtil}</h1>
 * Utility class for downloading LLM JSON file.
 * This class provides a method to download the top 999 models from the LLM library.
 * It uses asynchronous HTTP requests to fetch the data and writes the response to a file.
 * The class is annotated with {@link Component} to indicate that it is a Spring-managed component.
 */
public class LLMLibraryUtil {

    private static final Logger log = LoggerFactory.getLogger(LLMLibraryUtil.class);
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    /**
     * <h3>{@link #LLMLibraryUtil()}</h3>
     * Default 0-args constructor.
     */
    LLMLibraryUtil() {}

    /**
     * <h3>{@link #downloadLLMJsonFile()}</h3>
     * Downloads the LLM JSON file from the specified URL.
     * This method creates a Flux that performs an asynchronous HTTP GET request to download the JSON data.
     * The response is written to a file named "llm.json".
     * If the HTTP request is successful, the response body is emitted to the Flux.
     * If the request fails, an error is emitted to the Flux.
     *
     * <pre>{@code
     * Flux<Object> flux = LLMLibraryUtil.downloadLLMJsonFile();
     * flux.subscribe(
     *     data -> System.out.println("Received data: " + data),
     *     error -> System.err.println("Error: " + error),
     *     () -> System.out.println("Download complete")
     * );
     * }</pre>
     *
     * @return a Flux that emits the JSON response body or an error if the request fails.
     */
    public static Flux<Object> downloadLLMJsonFile() {
        return Flux.create(sink -> {
            String urlString = "https://huggingface.co/api/models";
            log.info("Downloading JSON from URL: {}", urlString);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            responseFuture.thenAccept(response -> {
                if (response.statusCode() == 200) {
                    try {
                        Files.writeString(Paths.get("llm.json"), response.body(), StandardOpenOption.CREATE);
                        sink.next(response.body());
                        sink.complete();
                    } catch (IOException llmLibraryUtilExc) {
                        sink.error(llmLibraryUtilExc);
                    }
                } else {
                    sink.error(new IOException(RED + "HTTP request from LLMLibraryUtil failed with response code " + RESET + response.statusCode()));
                }
            }).exceptionally(exc -> {
                sink.error(exc);
                return null;
            });
        }).delayElements(Duration.ofMillis(100));
    }
}
