package org.dacss.projectinitai.downloaders;

import org.dacss.projectinitai.checksums.ChecksumActions;
import org.dacss.projectinitai.checksums.ChecksumsIface;
import org.dacss.projectinitai.directories.DirectoriesIface;
import org.dacss.projectinitai.directories.DirectoryActions;
import org.dacss.projectinitai.security.SecurityActions;
import org.dacss.projectinitai.security.SecurityIface;
import org.dacss.projectinitai.tar.TarActions;
import org.dacss.projectinitai.tar.TarIface;
import org.dacss.projectinitai.zip.ZipActions;
import org.dacss.projectinitai.zip.ZipIface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>{@link LLMDownloader}</h1>
 * This class is responsible for downloading a Large Language Model (LLM) from a specified URL.
 * It uses the Reactor framework to handle asynchronous operations and provides functionality to download,
 * decompress {@link TarIface} or {@link ZipIface}, generate directories {@link DirectoriesIface},
 * files and generate checksums {@link ChecksumsIface} for the downloaded files.
 */
@Component
public class LLMDownloader {

    private static final Logger log = LoggerFactory.getLogger(LLMDownloader.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";
    static String modelId;
    private static String baseUrl;

    /**
     * <h3>{@link LLMDownloader}</h3>
     *
     * @param modelId The ID of the model to be downloaded.
     */
    public LLMDownloader(String modelId) {
        LLMDownloader.modelId = modelId;
    }

    /**
     * <h3>{@link #downloadLLMModel()}</h3>
     * Downloads the LLM model from the specified URL.
     * The method performs the following steps:
     * <ul>
     *     <li>Calls {@link SecurityIface#processSecureAction(SecurityActions)} to get the API token.</li>
     *     <li>Constructs the final URL using the base URL, model ID, and API token.</li>
     *     <li>Sends an asynchronous HTTP GET request to the URL.</li>
     *     <li>Writes the response body to a file in the appropriate directory.</li>
     *     <li>Creates a new directory for the downloaded file.</li>
     *     <li>Decompresses the file if it is in a compressed format.</li>
     *     <li>Generates checksums for the downloaded file.</li>
     * </ul>
     * <p>
     * The method uses two paths:
     * <ul>
     *     <li><b><code>productionPath</code></b>: Points to the user's home directory for production use.</li>
     *     <li><b><code>devPath</code></b>: Points to the root of the project for development use.</li>
     * </ul>
     *
     * @return A {@link Flux} that emits the response body and completes when the process is finished.
     */
    public static Flux<Object> downloadLLMModel() {
        return SecurityIface.processSecureAction(SecurityActions.API_TOKEN)
                .flatMap(apiToken -> Flux.create(sink -> {
                    baseUrl = "https://huggingface.co/models";
                    String finalUrl = baseUrl + "/" + modelId + "?api_token=" + apiToken;
                    log.info("Final URL: {}", finalUrl);

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(finalUrl))
                            .GET()
                            .build();

                    CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

                    responseFuture.thenAccept(response -> {
                        if (response.statusCode() == 200) {
                            try {
                                String userHome = System.getProperty("user.home");
                                String productionPath = userHome + "/.project-ai-initializer/models/";
                                String devPath = "home/pai/.project-ai-initializer/models/";

                                String filePath = Files.exists(Paths.get(productionPath)) ? productionPath : devPath;
                                filePath += modelId; // Use the modelId as the file name

                                Files.writeString(Paths.get(filePath), response.body(), StandardOpenOption.CREATE);
                                sink.next(response.body());

                                // Create new directory
                                String finalFilePath = filePath;
                                DirectoriesIface.processDirFileAction(DirectoryActions.CREATE_DIRECTORY, filePath, null)
                                        .thenMany(Flux.defer(() -> {
                                            // Decompress the file
                                            if (finalFilePath.endsWith(".zip")) {
                                                return Flux.just(ZipIface.processZipAction(ZipActions.EXTRACT));
                                            } else if (finalFilePath.endsWith(".tar") || finalFilePath.endsWith(".tar.gz")) {
                                                return Flux.just(TarIface.processTarAction(TarActions.EXTRACT));
                                            } else {
                                                return Flux.error(new IllegalArgumentException(RED + "Unsupported file format" + RESET));
                                            }
                                        }))
                                        .thenMany(Flux.defer(() -> {
                                            // Generate checksums
                                            return ChecksumsIface.processChecksumAction(ChecksumActions.GENERATE);
                                        }))
                                        .subscribe(
                                                result -> log.info("{}Operation completed{}", GREEN, RESET),
                                                error -> log.error("{}Error handling operation{}", RED, RESET, error)
                                        );

                                sink.complete();
                            } catch (Exception llmDownloaderExc) {
                                sink.error(llmDownloaderExc);
                            }
                        } else {
                            sink.error(new IOException(RED + "Failed to download model" + RESET));
                        }
                    }).exceptionally(exc -> {
                        sink.error(exc);
                        return null;
                    });
                }));
    }
}
